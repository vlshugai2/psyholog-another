package vladyslav.shuhai.psyhology.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import vladyslav.shuhai.psyhology.entity.Event;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.time.LocalDate;
@Getter
@Setter
public class EventResponse {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


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
    public EventResponse(Event event) {
        this.id = event.getId();
        this.groupName = event.getGroupName();
        this.startDate = event.getStartDate();
        this.finishDate = event.getFinishDate();
        this.startTime = event.getStartTime();
        this.finishTime = event.getFinishTime();
        this.location = event.getLocation();
        this.description = event.getDescription();
        this.imgPath = event.getImgPath();
    }


}
