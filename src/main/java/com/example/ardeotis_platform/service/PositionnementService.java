package com.example.ardeotis_platform.service;

import com.example.ardeotis_platform.dto.request.UpdatePositionnementStatusRequest;
import com.example.ardeotis_platform.dto.response.HistoriquePositionnementResponseDto;
import com.example.ardeotis_platform.exception.BusinessException;
import com.example.ardeotis_platform.model.HistoriquePositionnement;
import com.example.ardeotis_platform.model.Positionnement;
import com.example.ardeotis_platform.model.PositionnementStatus;
import com.example.ardeotis_platform.repository.HistoriquePositionRepository;
import com.example.ardeotis_platform.repository.PositionnementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class PositionnementService {
    private final PositionnementRepository positionnementRepository;
    private final HistoriquePositionRepository historiquePositionRepository;

    private static final Map<PositionnementStatus, Set<PositionnementStatus>> ALLOWED_TRANSITIONS = Map.of(
            PositionnementStatus.INTERET_EXPRIME, Set.of(PositionnementStatus.PRESENTE_AU_CLIENT),
            PositionnementStatus.PRESENTE_AU_CLIENT, Set.of(PositionnementStatus.ENTRETIEN_PLANIFIE,PositionnementStatus.REFUSE),
            PositionnementStatus.ENTRETIEN_PLANIFIE, Set.of(PositionnementStatus.RETOUR_CLIENT_EN_ATTENTE,PositionnementStatus.REFUSE),
            PositionnementStatus.RETOUR_CLIENT_EN_ATTENTE, Set.of(PositionnementStatus.VALIDE,PositionnementStatus.REFUSE),
            PositionnementStatus.VALIDE, Set.of(),
            PositionnementStatus.REFUSE, Set.of()
            );

    public Positionnement updateStatus(Long id, UpdatePositionnementStatusRequest newStatus){

        Positionnement posi = positionnementRepository.findById(id).orElseThrow(() -> new BusinessException("Positionnement introuvbable"));
        PositionnementStatus currentStatus = posi.getStatus();
        if(! ALLOWED_TRANSITIONS.getOrDefault(currentStatus,Set.of()).contains(newStatus.getStatus())){
            throw new BusinessException("Transition de statut non autorisée");
        }
        posi.setStatus(newStatus.getStatus());
        posi.setLastStatusUpdateAt(LocalDateTime.now());
        Positionnement x = positionnementRepository.save(posi);
        HistoriquePositionnement historiquePositionnement = HistoriquePositionnement.builder().
                                                                                    positionnement(x)
                .newStatus(newStatus.getStatus()).ancienStatus(currentStatus).lastStatusUpdateAt(LocalDateTime.now())
                .build();
        historiquePositionRepository.save(historiquePositionnement);
        return x;
    }

    public List<HistoriquePositionnementResponseDto> getHistorique(Long id) {
        return historiquePositionRepository.findByPositionnementIdOrderByLastStatusUpdatedAtDesc(id).stream()
                                           .map(h -> new HistoriquePositionnementResponseDto(h.getId(),h.getAncienStatus(),h.getNewStatus(),h.getLastStatusUpdateAt(), h.getCommentaire())).toList();
    }
}
