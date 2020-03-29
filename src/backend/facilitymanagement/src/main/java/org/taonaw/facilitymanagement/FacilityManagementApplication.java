package org.taonaw.facilitymanagement;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.taonaw.facilitymanagement.domain.model.equipment.Equipment;
import org.taonaw.facilitymanagement.domain.model.equipment.EquipmentName;
import org.taonaw.facilitymanagement.domain.model.equipment.EquipmentStocks;
import org.taonaw.facilitymanagement.domain.model.equipmentcategory.EquipmentCategory;
import org.taonaw.facilitymanagement.domain.model.equipmentcategory.EquipmentCategoryName;
import org.taonaw.facilitymanagement.domain.model.studio.StartTimeType;
import org.taonaw.facilitymanagement.domain.model.studio.Studio;
import org.taonaw.facilitymanagement.domain.model.studio.StudioName;
import org.taonaw.facilitymanagement.domain.model.studio.StudioRoomSize;
import org.taonaw.facilitymanagement.infrastructure.repository.EquipmentCategoryRepository;
import org.taonaw.facilitymanagement.infrastructure.repository.EquipmentRepository;
import org.taonaw.facilitymanagement.infrastructure.repository.StudioRepository;

// @EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@SpringBootApplication
public class FacilityManagementApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(FacilityManagementApplication.class, args);
	}

	@Override
	public void run(String... args) {
		var studioRepository = new StudioRepository();
		studioRepository.add(Studio.newStudio(
				new StudioName("1studio"),
				new StudioRoomSize(9),
				StartTimeType.ON_THE_HOUR));
		studioRepository.add(Studio.newStudio(
				new StudioName("2studio"),
				new StudioRoomSize(11),
				StartTimeType.ON_THE_HOUR));
		studioRepository.add(Studio.newStudio(
				new StudioName("3studio"),
				new StudioRoomSize(11),
				StartTimeType.ON_THE_HALF_HOUR));
		studioRepository.add(Studio.newStudio(
				new StudioName("4studio"),
				new StudioRoomSize(13),
				StartTimeType.ON_THE_HOUR));
		studioRepository.add(Studio.newStudio(
				new StudioName("5studio"),
				new StudioRoomSize(15),
				StartTimeType.ON_THE_HOUR));
		studioRepository.add(Studio.newStudio(
				new StudioName("6studio"),
				new StudioRoomSize(13),
				StartTimeType.ON_THE_HOUR));
		studioRepository.add(Studio.newStudio(
				new StudioName("7studio"),
				new StudioRoomSize(15),
				StartTimeType.ON_THE_HALF_HOUR));

		var equipmentCategoryRepository = new EquipmentCategoryRepository();
		var amplifier = EquipmentCategory.newEquipmentCategory(new EquipmentCategoryName("アンプ"));
		equipmentCategoryRepository.add(amplifier);
		var guitar = EquipmentCategory.newEquipmentCategory(new EquipmentCategoryName("ギター"));
		equipmentCategoryRepository.add(guitar);
		var bass = EquipmentCategory.newEquipmentCategory(new EquipmentCategoryName("ベース"));
		equipmentCategoryRepository.add(bass);
		var keyBord = EquipmentCategory.newEquipmentCategory(new EquipmentCategoryName("キーボード"));
		equipmentCategoryRepository.add(keyBord);

		var equipmentRepository = new EquipmentRepository();
		equipmentRepository.add(Equipment.newEquipment(
				new EquipmentName("Marshall JCM2000"),
				amplifier.getEquipmentCategoryId(),
				new EquipmentStocks(10)));
		equipmentRepository.add(Equipment.newEquipment(
				new EquipmentName("Roland JC-120"),
				amplifier.getEquipmentCategoryId(),
				new EquipmentStocks(10)));
		equipmentRepository.add(Equipment.newEquipment(
				new EquipmentName("Fender Japan:ストラトキャスター"),
				guitar.getEquipmentCategoryId(),
				new EquipmentStocks(2)));
		equipmentRepository.add(Equipment.newEquipment(
				new EquipmentName("Fender Japan:ジャズベース"),
				bass.getEquipmentCategoryId(),
				new EquipmentStocks(2)));
		equipmentRepository.add(Equipment.newEquipment(
				new EquipmentName("YAMAHA P-105"),
				keyBord.getEquipmentCategoryId(),
				new EquipmentStocks(5)));
	}
}
