package nimblix.in.HealthCareHub.utility;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class HealthCareUtil {

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");



    public static String changeCurrentTimeToLocalDateFromGmtToISTInString() {
        LocalDateTime gmtTime = LocalDateTime.now(ZoneOffset.UTC);
        ZoneId istZone = ZoneId.of("Asia/Kolkata");
        LocalDateTime istTime = gmtTime.atZone(ZoneOffset.UTC).withZoneSameInstant(istZone).toLocalDateTime();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return istTime.format(formatter);
    }



}
