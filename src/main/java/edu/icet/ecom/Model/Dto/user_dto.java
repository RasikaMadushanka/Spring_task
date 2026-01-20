package edu.icet.ecom.Model.Dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class user_dto {
    private Long id;
    private String name;
    private String email;
    private String tier;
}
