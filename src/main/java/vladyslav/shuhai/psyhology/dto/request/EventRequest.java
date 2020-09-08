package vladyslav.shuhai.psyhology.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class EventRequest {

    private String groupName;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy",
            timezone = "UTC")
    private LocalDate startDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy",
            timezone = "UTC")
    private LocalDate finishDate;

    private String startTime;

    private String finishTime;
    private String location;
    private String description;
    private String imgPath;
    private List<Long> userId;
}
