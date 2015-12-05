-- MySQL dump 10.13  Distrib 5.6.24, for osx10.8 (x86_64)
--
-- Host: localhost    Database: SATI
-- ------------------------------------------------------
-- Server version	5.5.42

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

DROP Database if EXISTS SATI;
Create database sati;
use sati;

--
-- Table structure for table `Boleto`
--



DROP TABLE IF EXISTS `Boleto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Boleto` (
  `idBoleto` int(11) NOT NULL,
  `DataPagamento` datetime NOT NULL,
  PRIMARY KEY (`idBoleto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Boleto`
--

LOCK TABLES `Boleto` WRITE;
/*!40000 ALTER TABLE `Boleto` DISABLE KEYS */;
/*!40000 ALTER TABLE `Boleto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `DadosBancarios`
--

DROP TABLE IF EXISTS `DadosBancarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `DadosBancarios` (
  `idDadosBancarios` int(11) NOT NULL AUTO_INCREMENT,
  `agencia` varchar(45) NOT NULL,
  `conta` varchar(45) NOT NULL,
  `tipo` varchar(45) NOT NULL,
  `carteira` int(11) NOT NULL,
  PRIMARY KEY (`idDadosBancarios`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `DadosBancarios`
--

LOCK TABLES `DadosBancarios` WRITE;
/*!40000 ALTER TABLE `DadosBancarios` DISABLE KEYS */;
INSERT INTO `DadosBancarios` VALUES (1,'1','1','Corrente',1),(2,'11','1','Corrente',1),(3,'Agencia','Conta','Tipo',1),(4,'Agencia','Conta','Tipo',1);
/*!40000 ALTER TABLE `DadosBancarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Edicao`
--

DROP TABLE IF EXISTS `Edicao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Edicao` (
  `idEdicao` int(11) NOT NULL AUTO_INCREMENT,
  `dataInicio` datetime NOT NULL,
  `dataFim` datetime NOT NULL,
  `agendaDefinida` tinyint(1) NOT NULL,
  `titulo` varchar(45) DEFAULT NULL,
  `tema` varchar(45) NOT NULL,
  `idDadosBancarios` int(11) DEFAULT NULL,
  PRIMARY KEY (`idEdicao`),
  KEY `Edicao_DadosBancarios_idx` (`idDadosBancarios`),
  CONSTRAINT `Edicao_DadosBancarios` FOREIGN KEY (`idDadosBancarios`) REFERENCES `DadosBancarios` (`idDadosBancarios`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=70 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Edicao`
--

LOCK TABLES `Edicao` WRITE;
/*!40000 ALTER TABLE `Edicao` DISABLE KEYS */;
INSERT INTO `Edicao` VALUES (69,'2000-03-01 14:48:59','2000-04-01 14:48:59',0,'Novo Título','Tema',NULL);
/*!40000 ALTER TABLE `Edicao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Participacao`
--

DROP TABLE IF EXISTS `Participacao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Participacao` (
  `idParticipacao` int(11) NOT NULL,
  `valida` tinyint(1) NOT NULL,
  `Presencao` tinyint(1) NOT NULL,
  `idProgramacao` int(11) NOT NULL,
  `IdPessoa` int(11) NOT NULL,
  `IdBoleto` int(11) NOT NULL,
  PRIMARY KEY (`idParticipacao`),
  KEY `Participacao_Programacao_idx` (`idProgramacao`),
  KEY `Participacao_Pessoa_idx` (`IdPessoa`),
  KEY `Participacao_Boleto_idx` (`IdBoleto`),
  CONSTRAINT `Participacao_Boleto` FOREIGN KEY (`IdBoleto`) REFERENCES `Boleto` (`idBoleto`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `Participacao_Pessoa` FOREIGN KEY (`IdPessoa`) REFERENCES `Pessoa` (`idPessoa`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `Participacao_Programacao` FOREIGN KEY (`idProgramacao`) REFERENCES `Programacao` (`idProgramacao`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Participacao`
--

LOCK TABLES `Participacao` WRITE;
/*!40000 ALTER TABLE `Participacao` DISABLE KEYS */;
/*!40000 ALTER TABLE `Participacao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Pessoa`
--

DROP TABLE IF EXISTS `Pessoa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Pessoa` (
  `idPessoa` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(250) NOT NULL,
  `tipo` varchar(45) NOT NULL,
  `ra` varchar(20) NOT NULL,
  `email` varchar(50) NOT NULL,
  `instituicao` varchar(100) DEFAULT NULL,
  `cpf` varchar(20) NOT NULL,
  `rg` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`idPessoa`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Pessoa`
--

LOCK TABLES `Pessoa` WRITE;
/*!40000 ALTER TABLE `Pessoa` DISABLE KEYS */;
INSERT INTO `Pessoa` VALUES (1,'Nome','Tipo','RA','email@valido.com','','377.894.178.00','RG'),(2,'Nome','Tipo','RA','email@valido.com','','377.894.178.00','RG'),(3,'Nome','Tipo','RA','email@valido.com','','377.894.178.00','RG'),(4,'Nome','Tipo','RA','email@valido.com','','377.894.178.00','RG'),(5,'Nomizinho','Tipo','RA','email@valido.com','','377.894.178.00','RG');
/*!40000 ALTER TABLE `Pessoa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Programacao`
--

DROP TABLE IF EXISTS `Programacao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Programacao` (
  `idProgramacao` int(11) NOT NULL AUTO_INCREMENT,
  `dataInicio` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `dataFim` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `custo` double NOT NULL,
  `custoResponsavel` double NOT NULL,
  `local` varchar(45) NOT NULL,
  `titulo` varchar(45) NOT NULL,
  `descricao` varchar(100) DEFAULT NULL,
  `capacidade` int(11) NOT NULL,
  `cargaHoraria` int(11) NOT NULL,
  `valorInscricao` double NOT NULL,
  `idEdicao` int(11) DEFAULT NULL,
  `idPessoa` int(11) DEFAULT NULL,
  PRIMARY KEY (`idProgramacao`),
  KEY `Programacao_Pessoa_Responsavel_idx` (`idPessoa`),
  KEY `Programacao_Edicao_idx` (`idEdicao`),
  CONSTRAINT `Programacao_Edicao` FOREIGN KEY (`idEdicao`) REFERENCES `Edicao` (`idEdicao`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `Programacao_Pessoa_Responsavel` FOREIGN KEY (`idPessoa`) REFERENCES `Pessoa` (`idPessoa`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Programacao`
--

LOCK TABLES `Programacao` WRITE;
/*!40000 ALTER TABLE `Programacao` DISABLE KEYS */;
/*!40000 ALTER TABLE `Programacao` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-12-05 15:58:46
