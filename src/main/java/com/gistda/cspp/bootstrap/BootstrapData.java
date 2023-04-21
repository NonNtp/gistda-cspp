package com.gistda.cspp.bootstrap;

import com.gistda.cspp.dto.AfImgCsvRecord;
import com.gistda.cspp.dto.AfModCsvRecord;
import com.gistda.cspp.entities.AfImg;
import com.gistda.cspp.entities.AfMod;
import com.gistda.cspp.repositories.AfImgRepository;
import com.gistda.cspp.repositories.AfModRepository;
import com.gistda.cspp.services.AfImgCsvService;
import com.gistda.cspp.services.AfModCsvService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BootstrapData implements CommandLineRunner {
    private final AfImgRepository afImgRepository ;
    private final AfImgCsvService afImgCsvService ;
    private final AfModRepository afModRepository ;
    private final AfModCsvService afModCsvService ;

    @Transactional
    @Override
    public void run(String... args) throws Exception {
        loadAfImgCsvData();
        loadAfModCsvData();
    }

    private void loadAfModCsvData() throws FileNotFoundException{
        if (afModRepository.count() < 10) {
            File file = ResourceUtils.getFile("classpath:csv/afmod.csv");

            List<AfModCsvRecord> recs1 = afModCsvService.convertAfModCsv(file) ;

            recs1.forEach(afModCsvRecord -> {
                afModRepository.save(AfMod.builder()
                        .viirsId("1")
                        .latitude(afModCsvRecord.getLatitudeCSV())
                        .longitude(afModCsvRecord.getLongitudeCSV())
                        .m13Brightness(afModCsvRecord.getM13Brightness())
                        .resolutionAlongScan(afModCsvRecord.getResolutionAlongScan())
                        .resolutionAlongTrack(afModCsvRecord.getResolutionAlongTrack())
                        .detectionConfidence(afModCsvRecord.getDetectionConfidence())
                        .firePower(afModCsvRecord.getFirePower())
                        .dataDate(afModCsvRecord.getDataDate())
                        .build());
            });

        }
    }

    private void loadAfImgCsvData() throws FileNotFoundException{
        if (afImgRepository.count() < 10) {
            File file = ResourceUtils.getFile("classpath:csv/afimg.csv");

            List<AfImgCsvRecord> recs2 = afImgCsvService.convertAfImgCsv(file) ;

            recs2.forEach(afImgCsvRecord -> {
                afImgRepository.save(AfImg.builder()
                        .viirsId("1")
                        .latitude(afImgCsvRecord.getLatitudeCSV())
                        .longitude(afImgCsvRecord.getLongitudeCSV())
                        .i04Brightness(afImgCsvRecord.getI04Brightness())
                        .resolutionAlongScan(afImgCsvRecord.getResolutionAlongScan())
                        .resolutionAlongTrack(afImgCsvRecord.getResolutionAlongTrack())
                        .detectionConfidence(afImgCsvRecord.getDetectionConfidence())
                        .firePower(afImgCsvRecord.getFirePower())
                        .persistentAnomaly(afImgCsvRecord.getPersistentAnomaly())
                        .dataDate(afImgCsvRecord.getDataDate())
                        .build());
            });
        }
    }
}
