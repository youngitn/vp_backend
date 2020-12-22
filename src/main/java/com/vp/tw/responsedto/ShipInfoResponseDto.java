package com.vp.tw.responsedto;

import java.util.List;

import com.vp.tw.entity.t100.Xmdk;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ShipInfoResponseDto {

	List<Xmdk> Data;
}
