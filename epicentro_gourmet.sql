CREATE DATABASE  IF NOT EXISTS `epicentro_gourmet` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `epicentro_gourmet`;
-- MySQL dump 10.13  Distrib 8.0.46, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: epicentro_gourmet
-- ------------------------------------------------------
-- Server version	8.0.46

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `cajero`
--

DROP TABLE IF EXISTS `cajero`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cajero` (
  `idPersonal` int NOT NULL,
  `turno` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`idPersonal`),
  CONSTRAINT `fk_cajero_personal` FOREIGN KEY (`idPersonal`) REFERENCES `personal` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cajero`
--

LOCK TABLES `cajero` WRITE;
/*!40000 ALTER TABLE `cajero` DISABLE KEYS */;
INSERT INTO `cajero` VALUES (3,'Tarde'),(5,'Mañana'),(7,'Tarde'),(9,'Mañana'),(10,'Mañana'),(11,'Noche'),(12,'Tarde'),(13,'Noche');
/*!40000 ALTER TABLE `cajero` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cocinero`
--

DROP TABLE IF EXISTS `cocinero`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cocinero` (
  `idPersonal` int NOT NULL,
  `especialidad` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `plusCategoria` double DEFAULT NULL,
  PRIMARY KEY (`idPersonal`),
  CONSTRAINT `fk_cocinero_personal` FOREIGN KEY (`idPersonal`) REFERENCES `personal` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cocinero`
--

LOCK TABLES `cocinero` WRITE;
/*!40000 ALTER TABLE `cocinero` DISABLE KEYS */;
INSERT INTO `cocinero` VALUES (1,'Parrilla',25000),(2,'Pastas',22000),(4,'Cocina regional',20000),(6,'Pizza',23000),(8,'Pastelería',24000),(14,'Parrilla',25000),(15,'Pastas',22000);
/*!40000 ALTER TABLE `cocinero` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detalle_pedido`
--

DROP TABLE IF EXISTS `detalle_pedido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detalle_pedido` (
  `idDetalle` bigint NOT NULL AUTO_INCREMENT,
  `idPedido` bigint NOT NULL,
  `idPlato` int NOT NULL,
  `cantidad` int NOT NULL,
  PRIMARY KEY (`idDetalle`),
  KEY `fk_detalle_pedido` (`idPedido`),
  KEY `fk_detalle_plato` (`idPlato`),
  CONSTRAINT `fk_detalle_pedido` FOREIGN KEY (`idPedido`) REFERENCES `pedido` (`idPedido`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_detalle_plato` FOREIGN KEY (`idPlato`) REFERENCES `plato` (`idPlato`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detalle_pedido`
--

LOCK TABLES `detalle_pedido` WRITE;
/*!40000 ALTER TABLE `detalle_pedido` DISABLE KEYS */;
INSERT INTO `detalle_pedido` VALUES (1,1,1,3),(2,1,2,2),(3,2,1,4),(4,2,4,2),(5,3,3,2),(6,3,2,2),(7,5,7,2),(8,5,9,1),(9,6,7,1),(10,6,8,2),(11,4,5,4),(12,4,6,2),(13,8,12,5),(14,8,13,3),(15,7,10,3),(16,7,11,4);
/*!40000 ALTER TABLE `detalle_pedido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `festival`
--

DROP TABLE IF EXISTS `festival`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `festival` (
  `idFestival` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `temporada` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `fechaInicio` date NOT NULL,
  `fechaFin` date NOT NULL,
  `costoSuperficie` double DEFAULT NULL,
  `plusElectricidad` double DEFAULT NULL,
  `costoMontaje` double DEFAULT NULL,
  `sueldoBase` double DEFAULT NULL,
  `valorAnioAntiguedad` double DEFAULT NULL,
  PRIMARY KEY (`idFestival`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `festival`
--

LOCK TABLES `festival` WRITE;
/*!40000 ALTER TABLE `festival` DISABLE KEYS */;
INSERT INTO `festival` VALUES (1,'Feria de Otoño','Otoño','2026-04-10','2026-04-13',1000,500,2000,300000,5000),(2,'Festival Sabores de Verano','Verano','2026-01-15','2026-01-18',1200,650,2500,320000,5500),(3,'Feria Primavera Gourmet','Primavera','2026-09-20','2026-09-22',1100,600,2200,310000,5200);
/*!40000 ALTER TABLE `festival` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `food_truck`
--

DROP TABLE IF EXISTS `food_truck`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `food_truck` (
  `idUnidad` int NOT NULL,
  `patente` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `requiereElectricidad` tinyint(1) NOT NULL,
  PRIMARY KEY (`idUnidad`),
  UNIQUE KEY `uk_foodtruck_patente` (`patente`),
  CONSTRAINT `fk_foodtruck_unidad` FOREIGN KEY (`idUnidad`) REFERENCES `unidad_de_venta` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `food_truck`
--

LOCK TABLES `food_truck` WRITE;
/*!40000 ALTER TABLE `food_truck` DISABLE KEYS */;
INSERT INTO `food_truck` VALUES (1,'AB123CD',1),(2,'AC456EF',0),(3,'AD789GH',1);
/*!40000 ALTER TABLE `food_truck` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pedido`
--

DROP TABLE IF EXISTS `pedido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pedido` (
  `idPedido` bigint NOT NULL AUTO_INCREMENT,
  `fecha` date NOT NULL,
  `idUnidad` int NOT NULL,
  PRIMARY KEY (`idPedido`),
  KEY `fk_pedido_unidad` (`idUnidad`),
  CONSTRAINT `fk_pedido_unidad` FOREIGN KEY (`idUnidad`) REFERENCES `unidad_de_venta` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedido`
--

LOCK TABLES `pedido` WRITE;
/*!40000 ALTER TABLE `pedido` DISABLE KEYS */;
INSERT INTO `pedido` VALUES (1,'2026-04-10',1),(2,'2026-04-11',1),(3,'2026-04-12',1),(4,'2026-01-16',2),(5,'2026-04-11',3),(6,'2026-04-12',3),(7,'2026-09-21',4),(8,'2026-01-17',5);
/*!40000 ALTER TABLE `pedido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `personal`
--

DROP TABLE IF EXISTS `personal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `personal` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `apellido` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `dni` varchar(30) COLLATE utf8mb4_unicode_ci NOT NULL,
  `fechaNacimiento` date DEFAULT NULL,
  `fechaIngreso` date DEFAULT NULL,
  `idUnidad` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_personal_dni` (`dni`),
  KEY `fk_personal_unidad` (`idUnidad`),
  CONSTRAINT `fk_personal_unidad` FOREIGN KEY (`idUnidad`) REFERENCES `unidad_de_venta` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personal`
--

LOCK TABLES `personal` WRITE;
/*!40000 ALTER TABLE `personal` DISABLE KEYS */;
INSERT INTO `personal` VALUES (1,'Ana','Gomez','30111222','1990-05-12','2022-03-01',1),(2,'Luis','Perez','31222333','1988-08-21','2021-06-15',1),(3,'Marta','Diaz','32333444','1992-11-03','2023-02-10',1),(4,'Carlos','Sosa','33444555','1985-02-17','2020-04-12',2),(5,'Laura','Fernandez','34555666','1991-07-29','2022-08-01',2),(6,'Diego','Lopez','35666777','1987-09-14','2021-05-20',3),(7,'Sofia','Martinez','36777888','1993-01-25','2024-01-10',3),(8,'Valentina','Romero','37888999','1995-04-30','2023-09-01',4),(9,'Jorge','Acosta','38999000','1984-12-11','2019-07-15',5),(10,'Martin','Ibarra','39000111','1994-06-08','2024-03-05',1),(11,'Rocio','Vera','40111222','1996-10-19','2023-11-20',3),(12,'José','Rodriguez','30555222','1990-04-12','2019-03-01',4),(13,'Arnaldo','Costas','31222111','1988-09-21','2018-04-15',4),(14,'Estella','Ruiz','30777444','1992-01-16','2023-06-10',5),(15,'Ernesto','Farias','33444654','1985-01-17','2019-04-12',2);
/*!40000 ALTER TABLE `personal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `plato`
--

DROP TABLE IF EXISTS `plato`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `plato` (
  `idPlato` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `precio` double NOT NULL,
  `costo` double NOT NULL,
  `idUnidad` int NOT NULL,
  PRIMARY KEY (`idPlato`),
  KEY `fk_plato_unidad` (`idUnidad`),
  CONSTRAINT `fk_plato_unidad` FOREIGN KEY (`idUnidad`) REFERENCES `unidad_de_venta` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `plato`
--

LOCK TABLES `plato` WRITE;
/*!40000 ALTER TABLE `plato` DISABLE KEYS */;
INSERT INTO `plato` VALUES (1,'Hamburguesa',8000,3500,1),(2,'Papas fritas',4000,1200,1),(3,'Choripán',5000,2200,1),(4,'Limonada',3000,900,1),(5,'Empanada de carne',2500,1000,2),(6,'Empanada de pollo',2500,950,2),(7,'Pizza muzzarella',7500,3000,3),(8,'Pizza especial',9500,4200,3),(9,'Limonada',3000,900,3),(10,'Torta de chocolate',6000,2500,4),(11,'Café',2500,700,4),(12,'Empanada de carne',2500,1000,5),(13,'Empanada de pollo',2500,950,5);
/*!40000 ALTER TABLE `plato` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `puesto_desarmable`
--

DROP TABLE IF EXISTS `puesto_desarmable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `puesto_desarmable` (
  `idUnidad` int NOT NULL,
  `cantCarpas` int NOT NULL,
  `tiempoMontaje` int NOT NULL,
  PRIMARY KEY (`idUnidad`),
  CONSTRAINT `fk_puesto_unidad` FOREIGN KEY (`idUnidad`) REFERENCES `unidad_de_venta` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `puesto_desarmable`
--

LOCK TABLES `puesto_desarmable` WRITE;
/*!40000 ALTER TABLE `puesto_desarmable` DISABLE KEYS */;
INSERT INTO `puesto_desarmable` VALUES (4,3,60),(5,2,45);
/*!40000 ALTER TABLE `puesto_desarmable` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `unidad_de_venta`
--

DROP TABLE IF EXISTS `unidad_de_venta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `unidad_de_venta` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `superficie` double NOT NULL,
  `codigo` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `idFestival` int NOT NULL,
  `idResponsable` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_unidad_codigo` (`codigo`),
  KEY `fk_unidad_festival` (`idFestival`),
  KEY `fk_unidad_responsable` (`idResponsable`),
  CONSTRAINT `fk_unidad_festival` FOREIGN KEY (`idFestival`) REFERENCES `festival` (`idFestival`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_unidad_responsable` FOREIGN KEY (`idResponsable`) REFERENCES `personal` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `unidad_de_venta`
--

LOCK TABLES `unidad_de_venta` WRITE;
/*!40000 ALTER TABLE `unidad_de_venta` DISABLE KEYS */;
INSERT INTO `unidad_de_venta` VALUES (1,'La Birra Truck',12.5,'FT00000001',1,1),(2,'Sabores del Barrio',15,'FT00000002',2,5),(3,'Pizza Express Truck',13.5,'FT00000003',1,6),(4,'Dulce Estación',20,'PD00000001',3,8),(5,'El Rincón Criollo',18,'PD00000002',2,9);
/*!40000 ALTER TABLE `unidad_de_venta` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-09-12 18:48:47
