package com.test.swagger.dto;

import com.test.swagger.model.EntityType;
import com.test.swagger.validation.ValidTestInput;
import com.test.swagger.validation.ValueOfEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ValidTestInput
public class TestInput {
    @NotNull
    private Long id;

    private Long otherId;

    @NotNull
    @ValueOfEnum(EntityType.class)
    private DictionaryItem entityType;

    @NotNull
    private Boolean active;
}
