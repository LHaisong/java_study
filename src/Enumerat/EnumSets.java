package Enumerat;

import java.util.EnumSet;

import static Enumerat.AlarmsPoints.*;

public class EnumSets {
	public static void main(String[]args){
		EnumSet<AlarmsPoints>points=
				EnumSet.noneOf(AlarmsPoints.class);
		points.add(BATHROOM);
		points.addAll(EnumSet.of(STAIR1,STAIR2,KITCHEN));
		System.out.println(points);
		System.out.println("-----------------------");
		points=EnumSet.allOf(AlarmsPoints.class);
		points.removeAll(EnumSet.of(STAIR1,STAIR2,KITCHEN));
		System.out.println(points);
		System.out.println("-----------------------");
		points.removeAll(EnumSet.range(OFFICE1,OFFICE2));
		System.out.println(points);
	}
}
