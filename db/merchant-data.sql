/*
SQLyog Enterprise - MySQL GUI v6.56
MySQL - 5.6.33 : Database - merchant
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- CREATE DATABASE /*!32312 IF NOT EXISTS*/`merchant` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `merchant`;

/*Data for the table `a_user` */

insert  into `a_user`(`id`,`username`,`name`,`surname`,`password`,`email`,`phone_code`,`phone`) values (1,'serozh','Seryozha','Hovhannisyan','c4ca4238a0b923820dcc509a6f75849b','serozh@connectto.com','374','93787377'),(2,'aram','Aram','Ter-Martirosyan','c4ca4238a0b923820dcc509a6f75849b','aram@connectto.com','1','18186419411');

/*Data for the table `c_privilege_lcp` */

insert  into `c_privilege_lcp`(`id`,`name`,`privilege`,`comment`) values (1,'Super admin','all','Chairman of the Supervisory Council'),(2,'Company admin','crud_company','create update delete company'),(3,'Branch admin','crud_branch','create update delete branch'),(4,'Cashier admin','crud_cashier','create update delete cashier'),(5,'Simple cashier','cashier','member');

/*Data for the table `g_country_lcp` */

insert  into `g_country_lcp`(`id`,`name`,`abr`,`phone_code`) values (1,'USA','us','1'),(2,'Armenia','am','374');

/*Data for the table `g_country_region_lcp` */

insert  into `g_country_region_lcp`(`id`,`name`,`abr`,`country_id`) values (1,'ALABAMA','AL',1),(2,'ALASKA','AK',1),(3,'ARIZONA','AR',1),(4,'ARKANSAS','AZ',1),(5,'CALIFORNIA','CA',1),(6,'COLORADO','CO',1),(7,'CONNECTICUT','CT',1),(8,'DELAWARE','DE',1),(9,'FLORIDA','FL',1),(10,'GEORGIA','GA',1),(11,'HAWAII','HI',1),(12,'IDAHO','ID',1),(13,'ILLINOIS','IL',1),(14,'INDIANA','IN',1),(15,'IOWA','IA',1),(16,'KANSAS','KS',1),(17,'KENTUCKY','KY',1),(18,'LOUISIANA','LA',1),(19,'MAINE','ME',1),(20,'MARYLAND','MD',1),(21,'MASSACHUSETTS','MA',1),(22,'MICHIGAN','MI',1),(23,'MINNESOTA','MN',1),(24,'MISSISSIPPI','MO',1),(25,'MISSOURI','MS',1),(26,'MONTANA','MT',1),(27,'NEBRASKA','NE',1),(28,'NEVADA','NV',1),(29,'NEW HAMPSHIRE','NH',1),(30,'NEW JERSEY','NJ',1),(31,'NEW MEXICO','NM',1),(32,'NEW YORK','NY',1),(33,'NORTH CAROLINA','NC',1),(34,'NORTH DAKOTA','ND',1),(35,'OHIO','OH',1),(36,'OKLAHOMA','OK',1),(37,'OREGON','OR',1),(38,'PENNSYLVANIA','PA',1),(39,'RHODE ISLAND','RI',1),(40,'SOUTH CAROLINA','SC',1),(41,'SOUTH DAKOTA','SD',1),(42,'TENNESSEE','TN',1),(43,'TEXAS','TX',1),(44,'UTAH','UT',1),(45,'VERMONT','VT',1),(46,'VIRGINIA','VA',1),(47,'WASHINGTON','WA',1),(48,'WASHINGTON D.C.','DC',1),(49,'WEST VIRGINIA','WV',1),(50,'WISCONSIN','WI',1),(51,'WYOMING','WY',1),(52,'Aragatsotn',NULL,2),(53,'Ararat',NULL,2),(54,'Armavir',NULL,2),(55,'Gegharkunik',NULL,2),(56,'Kotayk',NULL,2),(57,'Lori',NULL,2),(58,'Shirak',NULL,2),(59,'Syunik',NULL,2),(60,'Tavush',NULL,2),(61,'Vayots Dzor',NULL,2),(62,'Yerevan',NULL,2);

/*Data for the table `g_currency_type_lcp` */

insert  into `g_currency_type_lcp`(`id`,`code`,`name`) values (1,'AED','United Arab Emirates Dirham (AED)'),(2,'AFN','Afghan Afghani (AFN)'),(3,'ALL','Albanian Lek (ALL)'),(4,'AMD','Armenian Dram (AMD)'),(5,'ANG','Netherlands Antillean Guilder (ANG)'),(6,'AOA','Angolan Kwanza (AOA)'),(7,'ARS','Argentine Peso (ARS)'),(8,'AUD','Australian Dollar (A$)'),(9,'AWG','Aruban Florin (AWG)'),(10,'AZN','Azerbaijani Manat (AZN)'),(11,'BAM','Bosnia-Herzegovina Convertible Mark (BAM)'),(12,'BBD','Barbadian Dollar (BBD)'),(13,'BDT','Bangladeshi Taka (BDT)'),(14,'BGN','Bulgarian Lev (BGN)'),(15,'BHD','Bahraini Dinar (BHD)'),(16,'BIF','Burundian Franc (BIF)'),(17,'BMD','Bermudan Dollar (BMD)'),(18,'BND','Brunei Dollar (BND)'),(19,'BOB','Bolivian Boliviano (BOB)'),(20,'BRL','Brazilian Real (R$)'),(21,'BSD','Bahamian Dollar (BSD)'),(22,'BTC','Bitcoin (฿)'),(23,'BTN','Bhutanese Ngultrum (BTN)'),(24,'BWP','Botswanan Pula (BWP)'),(25,'BYR','Belarusian Ruble (BYR)'),(26,'BZD','Belize Dollar (BZD)'),(27,'CAD','Canadian Dollar (CA$)'),(28,'CDF','Congolese Franc (CDF)'),(29,'CHF','Swiss Franc (CHF)'),(30,'CLF','Chilean Unit of Account (UF) (CLF)'),(31,'CLP','Chilean Peso (CLP)'),(32,'CNH','CNH (CNH)'),(33,'CNY','Chinese Yuan (CN¥)'),(34,'COP','Colombian Peso (COP)'),(35,'CRC','Costa Rican Colón (CRC)'),(36,'CUP','Cuban Peso (CUP)'),(37,'CVE','Cape Verdean Escudo (CVE)'),(38,'CZK','Czech Republic Koruna (CZK)'),(39,'DEM','German Mark (DEM)'),(40,'DJF','Djiboutian Franc (DJF)'),(41,'DKK','Danish Krone (DKK)'),(42,'DOP','Dominican Peso (DOP)'),(43,'DZD','Algerian Dinar (DZD)'),(44,'EGP','Egyptian Pound (EGP)'),(45,'ERN','Eritrean Nakfa (ERN)'),(46,'ETB','Ethiopian Birr (ETB)'),(47,'EUR','Euro (€)'),(48,'FIM','Finnish Markka (FIM)'),(49,'FJD','Fijian Dollar (FJD)'),(50,'FKP','Falkland Islands Pound (FKP)'),(51,'FRF','French Franc (FRF)'),(52,'GBP','British Pound Sterling (£)'),(53,'GEL','Georgian Lari (GEL)'),(54,'GHS','Ghanaian Cedi (GHS)'),(55,'GIP','Gibraltar Pound (GIP)'),(56,'GMD','Gambian Dalasi (GMD)'),(57,'GNF','Guinean Franc (GNF)'),(58,'GTQ','Guatemalan Quetzal (GTQ)'),(59,'GYD','Guyanaese Dollar (GYD)'),(60,'HKD','Hong Kong Dollar (HK$)'),(61,'HNL','Honduran Lempira (HNL)'),(62,'HRK','Croatian Kuna (HRK)'),(63,'HTG','Haitian Gourde (HTG)'),(64,'HUF','Hungarian Forint (HUF)'),(65,'IDR','Indonesian Rupiah (IDR)'),(66,'IEP','Irish Pound (IEP)'),(67,'ILS','Israeli New Sheqel (₪)'),(68,'INR','Indian Rupee (Rs.)'),(69,'IQD','Iraqi Dinar (IQD)'),(70,'IRR','Iranian Rial (IRR)'),(71,'ISK','Icelandic Króna (ISK)'),(72,'ITL','Italian Lira (ITL)'),(73,'JMD','Jamaican Dollar (JMD)'),(74,'JOD','Jordanian Dinar (JOD)'),(75,'JPY','Japanese Yen (¥)'),(76,'KES','Kenyan Shilling (KES)'),(77,'KGS','Kyrgystani Som (KGS)'),(78,'KHR','Cambodian Riel (KHR)'),(79,'KMF','Comorian Franc (KMF)'),(80,'KPW','North Korean Won (KPW)'),(81,'KRW','South Korean Won (₩)'),(82,'KWD','Kuwaiti Dinar (KWD)'),(83,'KYD','Cayman Islands Dollar (KYD)'),(84,'KZT','Kazakhstani Tenge (KZT)'),(85,'LAK','Laotian Kip (LAK)'),(86,'LBP','Lebanese Pound (LBP)'),(87,'LKR','Sri Lankan Rupee (LKR)'),(88,'LRD','Liberian Dollar (LRD)'),(89,'LSL','Lesotho Loti (LSL)'),(90,'LTL','Lithuanian Litas (LTL)'),(91,'LVL','Latvian Lats (LVL)'),(92,'LYD','Libyan Dinar (LYD)'),(93,'MAD','Moroccan Dirham (MAD)'),(94,'MDL','Moldovan Leu (MDL)'),(95,'MGA','Malagasy Ariary (MGA)'),(96,'MKD','Macedonian Denar (MKD)'),(97,'MMK','Myanmar Kyat (MMK)'),(98,'MNT','Mongolian Tugrik (MNT)'),(99,'MOP','Macanese Pataca (MOP)'),(100,'MRO','Mauritanian Ouguiya (MRO)'),(101,'MUR','Mauritian Rupee (MUR)'),(102,'MVR','Maldivian Rufiyaa (MVR)'),(103,'MWK','Malawian Kwacha (MWK)'),(104,'MXN','Mexican Peso (MX$)'),(105,'MYR','Malaysian Ringgit (MYR)'),(106,'MZN','Mozambican Metical (MZN)'),(107,'NAD','Namibian Dollar (NAD)'),(108,'NGN','Nigerian Naira (NGN)'),(109,'NIO','Nicaraguan Córdoba (NIO)'),(110,'NOK','Norwegian Krone (NOK)'),(111,'NPR','Nepalese Rupee (NPR)'),(112,'NZD','New Zealand Dollar (NZ$)'),(113,'OMR','Omani Rial (OMR)'),(114,'PAB','Panamanian Balboa (PAB)'),(115,'PEN','Peruvian Nuevo Sol (PEN)'),(116,'PGK','Papua New Guinean Kina (PGK)'),(117,'PHP','Philippine Peso (Php)'),(118,'PKG','PKG (PKG)'),(119,'PKR','Pakistani Rupee (PKR)'),(120,'PLN','Polish Zloty (PLN)'),(121,'PYG','Paraguayan Guarani (PYG)'),(122,'QAR','Qatari Rial (QAR)'),(123,'RON','Romanian Leu (RON)'),(124,'RSD','Serbian Dinar (RSD)'),(125,'RUB','Russian Ruble (RUB)'),(126,'RWF','Rwandan Franc (RWF)'),(127,'SAR','Saudi Riyal (SAR)'),(128,'SBD','Solomon Islands Dollar (SBD)'),(129,'SCR','Seychellois Rupee (SCR)'),(130,'SDG','Sudanese Pound (SDG)'),(131,'SEK','Swedish Krona (SEK)'),(132,'SGD','Singapore Dollar (SGD)'),(133,'SHP','Saint Helena Pound (SHP)'),(134,'SLL','Sierra Leonean Leone (SLL)'),(135,'SOS','Somali Shilling (SOS)'),(136,'SRD','Surinamese Dollar (SRD)'),(137,'STD','São Tomé and Príncipe Dobra (STD)'),(138,'SVC','Salvadoran Colón (SVC)'),(139,'SYP','Syrian Pound (SYP)'),(140,'SZL','Swazi Lilangeni (SZL)'),(141,'THB','Thai Baht (฿)'),(142,'TJS','Tajikistani Somoni (TJS)'),(143,'TMT','Turkmenistani Manat (TMT)'),(144,'TND','Tunisian Dinar (TND)'),(145,'TOP','Tongan Paʻanga (TOP)'),(146,'TRY','Turkish Lira (TRY)'),(147,'TTD','Trinidad and Tobago Dollar (TTD)'),(148,'TWD','New Taiwan Dollar (NT$)'),(149,'TZS','Tanzanian Shilling (TZS)'),(150,'UAH','Ukrainian Hryvnia (UAH)'),(151,'UGX','Ugandan Shilling (UGX)'),(152,'USD','US Dollar ($)'),(153,'UYU','Uruguayan Peso (UYU)'),(154,'UZS','Uzbekistan Som (UZS)'),(155,'VEF','Venezuelan Bolívar (VEF)'),(156,'VND','Vietnamese Dong (₫)'),(157,'VUV','Vanuatu Vatu (VUV)'),(158,'WST','Samoan Tala (WST)'),(159,'XAF','CFA Franc BEAC (FCFA)'),(160,'XCD','East Caribbean Dollar (EC$)'),(161,'XDR','Special Drawing Rights (XDR)'),(162,'XOF','CFA Franc BCEAO (CFA)'),(163,'XPF','CFP Franc (CFPF)'),(164,'YER','Yemeni Rial (YER)'),(165,'ZAR','South African Rand (ZAR)'),(166,'ZMK','Zambian Kwacha (1968–2012) (ZMK)'),(167,'ZMW','Zambian Kwacha (ZMW)'),(168,'ZWL','Zimbabwean Dollar (2009) (ZWL)');

/*Data for the table `g_language_lcp` */

insert  into `g_language_lcp`(`value`,`key`,`title`,`locale`) values (1,'hy','Armenian','hy'),(2,'en','English','en'),(3,'ru','Russian','ru'),(4,'fr','France','fr'),(5,'es','Spanish','es'),(6,'fa','Persian','fa');

/*Data for the table `status_lcp` */

insert  into `status_lcp`(`key`,`status`) values (1,'active'),(2,'converted'),(3,'deleted'),(4,'hidden'),(5,'blocked'),(6,'unverified'),(7,'unconverted'),(8,'pending');

/*Data for the table `t_rational_duration_lcp` */

insert  into `t_rational_duration_lcp`(`id`,`type`,`duration`) values (1,'MINUTES_5','300'),(2,'MINUTES_15','900'),(3,'HALF_HOUR','1800'),(4,'HOUR','3600'),(5,'DAY','86400');

/*Data for the table `t_state_lcp` */

insert  into `t_state_lcp`(`id`,`state`,`description`) values (1,'prepare transaction','prepare transaction'),(2,'pending transaction','transaction was sent from merchant waiting wallet answer'),(3,'time outed transaction','transaction time expired wallet not answered'),(4,'canceled transaction','transaction canceled by wallet'),(5,'released transaction','transaction successfully released');

/*Data for the table `t_tax_type_lcp` */

insert  into `t_tax_type_lcp`(`id`,`type`) values (1,'min'),(2,'percent'),(3,'percent');

/*Data for the table `u_online_status_lcp` */

insert  into `u_online_status_lcp`(`key`,`status`) values (1,'Online'),(2,'Away'),(3,'Do not Disturb'),(4,'Invisible'),(5,'Offline');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
