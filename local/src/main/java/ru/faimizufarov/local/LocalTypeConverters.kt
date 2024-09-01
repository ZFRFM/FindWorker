package ru.faimizufarov.local

import androidx.room.TypeConverter
import ru.faimizufarov.domain.models.Address
import ru.faimizufarov.domain.models.Button
import ru.faimizufarov.domain.models.Experience
import ru.faimizufarov.domain.models.Salary

class LocalTypeConverters {

    @TypeConverter
    fun fromButtonToString(button: Button): String {
        return "${button.text}"
    }

    @TypeConverter
    fun fromStringToButton(value: String): Button {
        return Button(text = value)
    }

    @TypeConverter
    fun fromAddressToString(address: Address): String {
        return "${address.town},${address.street},${address.house}"
    }

    @TypeConverter
    fun fromStringToAddress(value: String): Address {
        val parts = value.split(",")
        return Address(parts[0], parts[1], parts[2])
    }

    @TypeConverter
    fun fromExperienceToString(experience: Experience): String {
        return "${experience.previewText},${experience.text}"
    }

    @TypeConverter
    fun fromStringToExperience(value: String): Experience {
        val parts = value.split(",")
        return Experience(parts[0], parts[1])
    }

    @TypeConverter
    fun fromSalaryToString(salary: Salary): String {
        return "${salary.short},${salary.full}"
    }

    @TypeConverter
    fun fromStringToSalary(value: String): Salary {
        val parts = value.split(",")
        return Salary(parts[0], parts[1])
    }

    @TypeConverter
    fun fromListToString(list: List<String>): String {
        return list.joinToString(",")
    }

    @TypeConverter
    fun fromStringToList(value: String): List<String> {
        return value.split(",")
    }
}