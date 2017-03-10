/*
SQLyog Community v12.2.6 (64 bit)
MySQL - 5.6.33 : Database - merchant
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
-- CREATE DATABASE /*!32312 IF NOT EXISTS*/`merchant` /*!40100 DEFAULT CHARACTER SET utf8 */;

-- USE `merchant`;

/*Table structure for table `t_wallet_deposit` */

DROP TABLE IF EXISTS `t_wallet_deposit`;

CREATE TABLE `t_wallet_deposit` (
  `id` int(15) unsigned NOT NULL AUTO_INCREMENT,
  `item_id` int(15) unsigned DEFAULT NULL,
  `name` varchar(64) COLLATE utf8_bin NOT NULL,
  `description` varchar(256) COLLATE utf8_bin NOT NULL,
  `start_at` datetime NOT NULL,
  `end_at` datetime DEFAULT NULL,
  `wallet_deposit_tax_id` int(15) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_t_wallet_deposit` (`wallet_deposit_tax_id`),
  CONSTRAINT `FK_t_wallet_deposit` FOREIGN KEY (`wallet_deposit_tax_id`) REFERENCES `t_wallet_deposit_tax` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

DROP TABLE IF EXISTS `t_wallet_deposit_tax`;

CREATE TABLE `t_wallet_deposit_tax` (
  `id` int(15) unsigned NOT NULL AUTO_INCREMENT,
  `transaction_id` int(15) unsigned DEFAULT NULL,
  `action_date` datetime NOT NULL,
  `wallet_id` int(15) unsigned NOT NULL,
  `wallet_ct` int(3) unsigned NOT NULL,
  `setup_id` int(15) unsigned NOT NULL,
  `setup_ct` int(3) unsigned NOT NULL,
  `paid_tax_to_wallet_setup` double(15,2) unsigned NOT NULL,
  `paid_tax_to_wallet_setup_price` double(15,2) unsigned NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `t_deposit` */

DROP TABLE IF EXISTS `t_deposit`;

CREATE TABLE `t_deposit` (
  `id` int(15) unsigned NOT NULL AUTO_INCREMENT,
  `final_state` int(1) unsigned NOT NULL,
  `wallet_id` int(15) unsigned NOT NULL,
  `cashier_cash_box_id` int(15) unsigned NOT NULL,
  `opened_at` datetime NOT NULL,
  `closed_at` datetime DEFAULT NULL,
  `deposit_amount` double(15,2) unsigned NOT NULL,
  `deposit_amount_ct` int(3) unsigned NOT NULL,
  `wallet_total_price` double(15,2) unsigned NOT NULL,
  `wallet_total_price_ct` int(3) unsigned NOT NULL,
  `deposit_cashier_cash_box_total_tax` double(15,2) unsigned NOT NULL,
  `deposit_cashier_cash_box_total_tax_ct` int(3) unsigned NOT NULL,
  `cashier_total_amount` double(15,2) unsigned NOT NULL,
  `cashier_total_amount_ct` int(3) unsigned NOT NULL,
  `wallet_deposit_id` int(15) unsigned NOT NULL,
  `process_start_id` int(15) unsigned NOT NULL,
  `process_end_id` int(15) unsigned DEFAULT NULL,
  `tax_id` int(15) unsigned NOT NULL,
  `is_encoded` int(1) unsigned NOT NULL,
  `order_code` varchar(64) COLLATE utf8_bin NOT NULL,
  `rational_duration` int(1) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_t_deposit_s` (`final_state`),
  KEY `FK_t_depositpe` (`process_end_id`),
  KEY `FK_t_depositps` (`process_start_id`),
  KEY `FK_t_deposittx` (`tax_id`),
  KEY `FK_t_deposit` (`wallet_deposit_id`),
  KEY `FK_t_depositcb` (`cashier_cash_box_id`),
  CONSTRAINT `FK_t_deposit` FOREIGN KEY (`wallet_deposit_id`) REFERENCES `t_wallet_deposit` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_t_deposit_s` FOREIGN KEY (`final_state`) REFERENCES `t_state_lcp` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_t_depositcb` FOREIGN KEY (`cashier_cash_box_id`) REFERENCES `cb_cashier_cash_box` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_t_depositpe` FOREIGN KEY (`process_end_id`) REFERENCES `t_deposit_process` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_t_depositps` FOREIGN KEY (`process_start_id`) REFERENCES `t_deposit_process` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_t_deposittx` FOREIGN KEY (`tax_id`) REFERENCES `t_deposit_tax` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `t_deposit_exchange` */

DROP TABLE IF EXISTS `t_deposit_exchange`;

CREATE TABLE `t_deposit_exchange` (
  `id` int(15) unsigned NOT NULL AUTO_INCREMENT,
  `wallet_id` int(15) unsigned NOT NULL,
  `cashier_cash_box_id` int(15) unsigned NOT NULL,
  `rate_id` int(15) unsigned NOT NULL,
  `exchange_date` datetime NOT NULL,
  `cashier_amount` double(15,2) unsigned NOT NULL,
  `cashier_ct` int(3) unsigned NOT NULL,
  `rate` double(15,2) unsigned NOT NULL,
  `rate_ct` int(3) unsigned NOT NULL,
  `wallet_bought_amount` double(15,2) unsigned NOT NULL,
  `wallet_bought_amount_ct` int(3) unsigned NOT NULL,
  `wallet_paid_amount` double(15,2) unsigned NOT NULL,
  `wallet_paid_ct` int(3) unsigned NOT NULL,
  `exchange_tax_id` int(15) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_t_deposit_exchange_ctc` (`cashier_ct`),
  KEY `FK_t_deposit_exchange_ctr` (`rate_ct`),
  KEY `FK_t_deposit_exchange_cta` (`wallet_bought_amount_ct`),
  KEY `FK_t_deposit_exchange_ctw` (`wallet_paid_ct`),
  KEY `FK_t_deposit_exchange_cbc` (`cashier_cash_box_id`),
  KEY `FK_t_deposit_exchange` (`exchange_tax_id`),
  CONSTRAINT `FK_t_deposit_exchange` FOREIGN KEY (`exchange_tax_id`) REFERENCES `t_deposit_exchange_tax` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_t_deposit_exchange_cbc` FOREIGN KEY (`cashier_cash_box_id`) REFERENCES `cb_cashier_cash_box` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `FK_t_deposit_exchange_cta` FOREIGN KEY (`wallet_bought_amount_ct`) REFERENCES `g_currency_type_lcp` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `FK_t_deposit_exchange_ctc` FOREIGN KEY (`cashier_ct`) REFERENCES `g_currency_type_lcp` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `FK_t_deposit_exchange_ctr` FOREIGN KEY (`rate_ct`) REFERENCES `g_currency_type_lcp` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `FK_t_deposit_exchange_ctw` FOREIGN KEY (`wallet_paid_ct`) REFERENCES `g_currency_type_lcp` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `t_deposit_exchange` */

/*Table structure for table `t_deposit_exchange_tax` */

DROP TABLE IF EXISTS `t_deposit_exchange_tax`;

CREATE TABLE `t_deposit_exchange_tax` (
  `id` int(15) unsigned NOT NULL AUTO_INCREMENT,
  `action_date` datetime NOT NULL,
  `wallet_id` int(15) unsigned NOT NULL,
  `cashier_cash_box_id` int(15) unsigned NOT NULL,
  `exchange_tax` double(15,2) unsigned NOT NULL,
  `exchange_tax_ct` int(3) unsigned NOT NULL,
  `exchange_tax_price` double(15,2) unsigned NOT NULL,
  `exchange_tax_price_ct` int(3) unsigned NOT NULL,
  `exchange_tax_type` int(1) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_t_deposit_exchange_taxe` (`exchange_tax_ct`),
  KEY `FK_t_deposit_exchange_taxp` (`exchange_tax_price_ct`),
  KEY `FK_t_deposit_exchange_taxcbc` (`cashier_cash_box_id`),
  KEY `FK_t_deposit_exchange_taxt` (`exchange_tax_type`),
  CONSTRAINT `FK_t_deposit_exchange_taxcbc` FOREIGN KEY (`cashier_cash_box_id`) REFERENCES `cb_cashier_cash_box` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `FK_t_deposit_exchange_taxe` FOREIGN KEY (`exchange_tax_ct`) REFERENCES `g_currency_type_lcp` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `FK_t_deposit_exchange_taxp` FOREIGN KEY (`exchange_tax_price_ct`) REFERENCES `g_currency_type_lcp` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `FK_t_deposit_exchange_taxt` FOREIGN KEY (`exchange_tax_type`) REFERENCES `t_tax_type_lcp` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `t_deposit_exchange_tax` */

/*Table structure for table `t_deposit_process` */

DROP TABLE IF EXISTS `t_deposit_process`;

CREATE TABLE `t_deposit_process` (
  `id` int(15) unsigned NOT NULL AUTO_INCREMENT,
  `state` int(1) unsigned NOT NULL,
  `action_date` datetime DEFAULT NULL,
  `wallet_id` int(15) unsigned NOT NULL,
  `cashier_cash_box_id` int(15) unsigned NOT NULL,
  `deposit_amount` double(15,2) unsigned NOT NULL,
  `deposit_amount_ct` int(3) unsigned NOT NULL,
  `wallet_deposit_price` double(15,2) unsigned NOT NULL,
  `wallet_total_price` double(15,2) unsigned NOT NULL,
  `wallet_total_price_cp` int(3) unsigned NOT NULL,
  `cashier_deposit_price` double(15,2) unsigned NOT NULL,
  `cashier_total_price` double(15,2) unsigned NOT NULL,
  `cashier_total_price_cp` int(3) unsigned NOT NULL,
  `process_tax_id` int(15) unsigned NOT NULL,
  `exchange_id` int(15) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_t_deposit_processs` (`state`),
  KEY `FK_t_deposit_processcbid` (`cashier_cash_box_id`),
  KEY `FK_t_deposit_processcta` (`deposit_amount_ct`),
  KEY `FK_t_deposit_processctw` (`wallet_total_price_cp`),
  KEY `FK_t_deposit_processctc` (`cashier_total_price_cp`),
  KEY `FK_t_deposit_processpt` (`process_tax_id`),
  KEY `FK_t_deposit_process` (`exchange_id`),
  CONSTRAINT `FK_t_deposit_process` FOREIGN KEY (`exchange_id`) REFERENCES `t_deposit_exchange` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_t_deposit_processcbid` FOREIGN KEY (`state`) REFERENCES `t_state_lcp` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `FK_t_deposit_processcta` FOREIGN KEY (`cashier_cash_box_id`) REFERENCES `cb_cashier_cash_box` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `FK_t_deposit_processctc` FOREIGN KEY (`cashier_total_price_cp`) REFERENCES `g_currency_type_lcp` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `FK_t_deposit_processctw` FOREIGN KEY (`wallet_total_price_cp`) REFERENCES `g_currency_type_lcp` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `FK_t_deposit_processpt` FOREIGN KEY (`process_tax_id`) REFERENCES `t_deposit_process_tax` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_t_deposit_processs` FOREIGN KEY (`state`) REFERENCES `t_state_lcp` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `t_deposit_process_tax` */

DROP TABLE IF EXISTS `t_deposit_process_tax`;

CREATE TABLE `t_deposit_process_tax` (
  `id` int(15) unsigned NOT NULL AUTO_INCREMENT,
  `action_date` datetime NOT NULL,
  `wallet_id` int(15) unsigned NOT NULL,
  `cashier_cash_box_id` int(15) unsigned NOT NULL,
  `process_tax` double(15,2) unsigned NOT NULL,
  `process_tax_total` double(15,2) unsigned NOT NULL,
  `process_tax_ct` int(3) unsigned NOT NULL,
  `process_tax_price` double(15,2) unsigned NOT NULL,
  `process_tax_price_total` double(15,2) unsigned NOT NULL,
  `process_tax_price_ct` int(3) unsigned NOT NULL,
  `process_tax_type` int(1) unsigned NOT NULL,
  `exchange_id` int(15) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_t_deposit_process_taxcb` (`cashier_cash_box_id`),
  KEY `FK_t_deposit_process_taxctt` (`process_tax_ct`),
  KEY `FK_t_deposit_process_taxctp` (`process_tax_price_ct`),
  CONSTRAINT `FK_t_deposit_process_taxcb` FOREIGN KEY (`cashier_cash_box_id`) REFERENCES `cb_cashier_cash_box` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `FK_t_deposit_process_taxctp` FOREIGN KEY (`process_tax_price_ct`) REFERENCES `g_currency_type_lcp` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `FK_t_deposit_process_taxctt` FOREIGN KEY (`process_tax_ct`) REFERENCES `g_currency_type_lcp` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `t_deposit_tax` */

DROP TABLE IF EXISTS `t_deposit_tax`;

CREATE TABLE `t_deposit_tax` (
  `id` int(15) unsigned NOT NULL AUTO_INCREMENT,
  `action_date` datetime NOT NULL,
  `wallet_id` int(15) unsigned NOT NULL,
  `cashier_cash_box_id` int(15) unsigned NOT NULL,
  `total_tax` double(15,2) unsigned NOT NULL,
  `total_tax_price` double(15,2) unsigned NOT NULL,
  `process_tax_id` int(15) unsigned NOT NULL,
  `exchange_tax_id` int(15) unsigned DEFAULT NULL,
  `wallet_deposit_tax_id` int(15) unsigned NOT NULL,
  `is_paid` int(1) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_t_deposit_taxcbid` (`cashier_cash_box_id`),
  KEY `FK_t_deposit_taxe` (`exchange_tax_id`),
  KEY `FK_t_deposit_taxp` (`process_tax_id`),
  KEY `FK_t_deposit_taxw` (`wallet_deposit_tax_id`),
  CONSTRAINT `FK_t_deposit_taxcbid` FOREIGN KEY (`cashier_cash_box_id`) REFERENCES `cb_cashier_cash_box` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `FK_t_deposit_taxe` FOREIGN KEY (`exchange_tax_id`) REFERENCES `t_deposit_exchange_tax` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_t_deposit_taxp` FOREIGN KEY (`process_tax_id`) REFERENCES `t_deposit_process_tax` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_t_deposit_taxw` FOREIGN KEY (`wallet_deposit_tax_id`) REFERENCES `t_wallet_deposit_tax` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
