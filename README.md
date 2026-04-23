# Flashcard System - Biydaalt 1

Энэхүү төсөл нь Java хэл дээр бүтээгдсэн, флаш-карт ашиглан мэдээллийг үр дүнтэй цээжлэхэд зориулагдсан команд мөрийн (CLI) програм юм.

##  Үндсэн боломжууд
- **Олон төрлийн эрэмбэлэлт:** `random`, `worst-first`, `recent-mistakes-first` горимуудтай.
- **Давталтын тохиргоо:** `--repetitions` ашиглан карт бүрийг хэдэн удаа зөв хариулж байж дуусгахыг шийднэ.
- **Карт хөрвүүлэлт:** `--invertCards` ашиглан асуулт, хариултын байрлалыг солих боломжтой.
- **Амжилтын систем:** Хэрэглэгчийн хурд болон хичээл зүтгэлийг үнэлж Achievements олгоно.

##  Ажиллуулах заавар

Програмыг ажиллуулах үндсэн формат:
```bash
java -cp target/flashcard-system-1.0-SNAPSHOT.jar edu.csa311.Main <cards-file> [options]

java -cp target/flashcard-system-1.0-SNAPSHOT.jar edu.csa311.Main cards.txt --order worst-first --repetitions 2

java -cp target/flashcard-system-1.0-SNAPSHOT.jar edu.csa311.Main cards.txt --order recent-mistakes-first --invertCards

java -cp target/flashcard-system-1.0-SNAPSHOT.jar edu.csa311.Main cards.txt --order worst-first

java -cp target/flashcard-system-1.0-SNAPSHOT.jar edu.csa311.Main cards.txt --order recent-mistakes-first 
