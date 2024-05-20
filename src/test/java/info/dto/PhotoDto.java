package info.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhotoDto {
    public  int albumId;
    public  int id;
    public  String title;
    public  String url;
    public String thumbnailUrl;

}
