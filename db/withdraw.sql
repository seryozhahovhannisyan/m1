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

/*Table structure for table `t_wallet_withdraw` */

DROP TABLE IF EXISTS `t_wallet_withdraw`;

CREATE TABLE `t_wallet_withdraw` (
  `id` int(15) unsigned NOT NULL AUTO_INCREMENT,
  `item_id` int(15) unsigned DEFAULT NULL,
  `name` varchar(64) COLLATE utf8_bin NOT NULL,
  `description` varchar(256) COLLATE utf8_bin NOT NULL,
  `start_at` datetime NOT NULL,
  `end_at` datetime DEFAULT NULL,
  `wallet_withdraw_tax_id` int(15) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_t_wallet_withdraw` (`wallet_withdraw_tax_id`),
  CONSTRAINT `FK_t_wallet_withdraw` FOREIGN KEY (`wallet_withdraw_tax_id`) REFERENCES `t_wallet_withdraw_tax` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `t_wallet_withdraw` */

insert  into `t_wallet_withdraw`(`id`,`item_id`,`name`,`description`,`start_at`,`end_at`,`wallet_withdraw_tax_id`) values 

(4,NULL,'WalletWithdraw name','WalletWithdraw description','2017-02-20 11:26:04','2017-02-20 11:31:04',4);

/*Table structure for table `t_wallet_withdraw_tax` */

DROP TABLE IF EXISTS `t_wallet_withdraw_tax`;

CREATE TABLE `t_wallet_withdraw_tax` (
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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `t_wallet_withdraw_tax` */

insert  into `t_wallet_withdraw_tax`(`id`,`transaction_id`,`action_date`,`wallet_id`,`wallet_ct`,`setup_id`,`setup_ct`,`paid_tax_to_wallet_setup`,`paid_tax_to_wallet_setup_price`) values 

(4,5,'2017-02-20 11:26:02',1705,152,6,152,0.24,11.64);

/*Table structure for table `t_withdraw` */

DROP TABLE IF EXISTS `t_withdraw`;

CREATE TABLE `t_withdraw` (
  `id` int(15) unsigned NOT NULL AUTO_INCREMENT,
  `final_state` int(1) unsigned NOT NULL,
  `wallet_id` int(15) unsigned NOT NULL,
  `cashier_cash_box_id` int(15) unsigned NOT NULL,
  `opened_at` datetime NOT NULL,
  `closed_at` datetime DEFAULT NULL,
  `withdraw_amount` double(15,2) unsigned NOT NULL,
  `withdraw_amount_ct` int(3) unsigned NOT NULL,
  `wallet_total_price` double(15,2) unsigned NOT NULL,
  `wallet_total_price_ct` int(3) unsigned NOT NULL,
  `withdraw_cashier_cash_box_total_tax` double(15,2) unsigned NOT NULL,
  `withdraw_cashier_cash_box_total_tax_ct` int(3) unsigned NOT NULL,
  `cashier_total_amount` double(15,2) unsigned NOT NULL,
  `cashier_total_amount_ct` int(3) unsigned NOT NULL,
  `wallet_withdraw_id` int(15) unsigned NOT NULL,
  `process_start_id` int(15) unsigned NOT NULL,
  `process_end_id` int(15) unsigned DEFAULT NULL,
  `tax_id` int(15) unsigned NOT NULL,
  `is_encoded` int(1) unsigned NOT NULL,
  `order_code` varchar(64) COLLATE utf8_bin NOT NULL,
  `rational_duration` int(1) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_t_withdraw_s` (`final_state`),
  KEY `FK_t_withdrawpe` (`process_end_id`),
  KEY `FK_t_withdrawps` (`process_start_id`),
  KEY `FK_t_withdrawtx` (`tax_id`),
  KEY `FK_t_withdraw` (`wallet_withdraw_id`),
  KEY `FK_t_withdrawcb` (`cashier_cash_box_id`),
  CONSTRAINT `FK_t_withdraw` FOREIGN KEY (`wallet_withdraw_id`) REFERENCES `t_wallet_withdraw` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_t_withdraw_s` FOREIGN KEY (`final_state`) REFERENCES `t_state_lcp` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_t_withdrawcb` FOREIGN KEY (`cashier_cash_box_id`) REFERENCES `cb_cashier_cash_box` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_t_withdrawpe` FOREIGN KEY (`process_end_id`) REFERENCES `t_withdraw_process` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_t_withdrawps` FOREIGN KEY (`process_start_id`) REFERENCES `t_withdraw_process` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_t_withdrawtx` FOREIGN KEY (`tax_id`) REFERENCES `t_withdraw_tax` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `t_withdraw` */

insert  into `t_withdraw`(`id`,`final_state`,`wallet_id`,`cashier_cash_box_id`,`opened_at`,`closed_at`,`withdraw_amount`,`withdraw_amount_ct`,`wallet_total_price`,`wallet_total_price_ct`,`withdraw_cashier_cash_box_total_tax`,`withdraw_cashier_cash_box_total_tax_ct`,`cashier_total_amount`,`cashier_total_amount_ct`,`wallet_withdraw_id`,`process_start_id`,`process_end_id`,`tax_id`,`is_encoded`,`order_code`,`rational_duration`) values 

(4,2,1705,19,'2017-02-20 11:26:04',NULL,12.00,152,11.64,152,0.12,152,11.88,152,4,4,NULL,4,1,'whjtjf',1);

/*Table structure for table `t_withdraw_exchange` */

DROP TABLE IF EXISTS `t_withdraw_exchange`;

CREATE TABLE `t_withdraw_exchange` (
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
  KEY `FK_t_withdraw_exchange_ctc` (`cashier_ct`),
  KEY `FK_t_withdraw_exchange_ctr` (`rate_ct`),
  KEY `FK_t_withdraw_exchange_cta` (`wallet_bought_amount_ct`),
  KEY `FK_t_withdraw_exchange_ctw` (`wallet_paid_ct`),
  KEY `FK_t_withdraw_exchange_cbc` (`cashier_cash_box_id`),
  KEY `FK_t_withdraw_exchange` (`exchange_tax_id`),
  CONSTRAINT `FK_t_withdraw_exchange` FOREIGN KEY (`exchange_tax_id`) REFERENCES `t_withdraw_exchange_tax` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_t_withdraw_exchange_cbc` FOREIGN KEY (`cashier_cash_box_id`) REFERENCES `cb_cashier_cash_box` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `FK_t_withdraw_exchange_cta` FOREIGN KEY (`wallet_bought_amount_ct`) REFERENCES `g_currency_type_lcp` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `FK_t_withdraw_exchange_ctc` FOREIGN KEY (`cashier_ct`) REFERENCES `g_currency_type_lcp` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `FK_t_withdraw_exchange_ctr` FOREIGN KEY (`rate_ct`) REFERENCES `g_currency_type_lcp` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `FK_t_withdraw_exchange_ctw` FOREIGN KEY (`wallet_paid_ct`) REFERENCES `g_currency_type_lcp` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `t_withdraw_exchange` */

/*Table structure for table `t_withdraw_exchange_tax` */

DROP TABLE IF EXISTS `t_withdraw_exchange_tax`;

CREATE TABLE `t_withdraw_exchange_tax` (
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
  KEY `FK_t_withdraw_exchange_taxe` (`exchange_tax_ct`),
  KEY `FK_t_withdraw_exchange_taxp` (`exchange_tax_price_ct`),
  KEY `FK_t_withdraw_exchange_taxcbc` (`cashier_cash_box_id`),
  KEY `FK_t_withdraw_exchange_taxt` (`exchange_tax_type`),
  CONSTRAINT `FK_t_withdraw_exchange_taxcbc` FOREIGN KEY (`cashier_cash_box_id`) REFERENCES `cb_cashier_cash_box` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `FK_t_withdraw_exchange_taxe` FOREIGN KEY (`exchange_tax_ct`) REFERENCES `g_currency_type_lcp` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `FK_t_withdraw_exchange_taxp` FOREIGN KEY (`exchange_tax_price_ct`) REFERENCES `g_currency_type_lcp` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `FK_t_withdraw_exchange_taxt` FOREIGN KEY (`exchange_tax_type`) REFERENCES `t_tax_type_lcp` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `t_withdraw_exchange_tax` */

/*Table structure for table `t_withdraw_process` */

DROP TABLE IF EXISTS `t_withdraw_process`;

CREATE TABLE `t_withdraw_process` (
  `id` int(15) unsigned NOT NULL AUTO_INCREMENT,
  `state` int(1) unsigned NOT NULL,
  `action_date` datetime DEFAULT NULL,
  `wallet_id` int(15) unsigned NOT NULL,
  `cashier_cash_box_id` int(15) unsigned NOT NULL,
  `withdraw_amount` double(15,2) unsigned NOT NULL,
  `withdraw_amount_ct` int(3) unsigned NOT NULL,
  `wallet_withdraw_price` double(15,2) unsigned NOT NULL,
  `wallet_total_price` double(15,2) unsigned NOT NULL,
  `wallet_total_price_cp` int(3) unsigned NOT NULL,
  `cashier_withdraw_price` double(15,2) unsigned NOT NULL,
  `cashier_total_price` double(15,2) unsigned NOT NULL,
  `cashier_total_price_cp` int(3) unsigned NOT NULL,
  `process_tax_id` int(15) unsigned NOT NULL,
  `exchange_id` int(15) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_t_withdraw_processs` (`state`),
  KEY `FK_t_withdraw_processcbid` (`cashier_cash_box_id`),
  KEY `FK_t_withdraw_processcta` (`withdraw_amount_ct`),
  KEY `FK_t_withdraw_processctw` (`wallet_total_price_cp`),
  KEY `FK_t_withdraw_processctc` (`cashier_total_price_cp`),
  KEY `FK_t_withdraw_processpt` (`process_tax_id`),
  KEY `FK_t_withdraw_process` (`exchange_id`),
  CONSTRAINT `FK_t_withdraw_process` FOREIGN KEY (`exchange_id`) REFERENCES `t_withdraw_exchange` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_t_withdraw_processcbid` FOREIGN KEY (`state`) REFERENCES `t_state_lcp` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `FK_t_withdraw_processcta` FOREIGN KEY (`cashier_cash_box_id`) REFERENCES `cb_cashier_cash_box` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `FK_t_withdraw_processctc` FOREIGN KEY (`cashier_total_price_cp`) REFERENCES `g_currency_type_lcp` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `FK_t_withdraw_processctw` FOREIGN KEY (`wallet_total_price_cp`) REFERENCES `g_currency_type_lcp` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `FK_t_withdraw_processpt` FOREIGN KEY (`process_tax_id`) REFERENCES `t_withdraw_process_tax` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_t_withdraw_processs` FOREIGN KEY (`state`) REFERENCES `t_state_lcp` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Data for the table `t_withdraw_process` */

insert  into `t_withdraw_process`(`id`,`state`,`action_date`,`wallet_id`,`cashier_cash_box_id`,`withdraw_amount`,`withdraw_amount_ct`,`wallet_withdraw_price`,`wallet_total_price`,`wallet_total_price_cp`,`cashier_withdraw_price`,`cashier_total_price`,`cashier_total_price_cp`,`process_tax_id`,`exchange_id`) values 

(4,2,'2017-02-20 11:26:04',1705,19,12.00,152,12.00,11.88,152,12.00,0.12,152,4,NULL);

/*Table structure for table `t_withdraw_process_tax` */

DROP TABLE IF EXISTS `t_withdraw_process_tax`;

CREATE TABLE `t_withdraw_process_tax` (
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
  KEY `FK_t_withdraw_process_taxcb` (`cashier_cash_box_id`),
  KEY `FK_t_withdraw_process_taxctt` (`process_tax_ct`),
  KEY `FK_t_withdraw_process_taxctp` (`process_tax_price_ct`),
  CONSTRAINT `FK_t_withdraw_process_taxcb` FOREIGN KEY (`cashier_cash_box_id`) REFERENCES `cb_cashier_cash_box` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `FK_t_withdraw_process_taxctp` FOREIGN KEY (`process_tax_price_ct`) REFERENCES `g_currency_type_lcp` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `FK_t_withdraw_process_taxctt` FOREIGN KEY (`process_tax_ct`) REFERENCES `g_currency_type_lcp` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `t_withdraw_process_tax` */

insert  into `t_withdraw_process_tax`(`id`,`action_date`,`wallet_id`,`cashier_cash_box_id`,`process_tax`,`process_tax_total`,`process_tax_ct`,`process_tax_price`,`process_tax_price_total`,`process_tax_price_ct`,`process_tax_type`,`exchange_id`) values 

(4,'2017-02-20 11:26:04',1705,19,0.12,0.12,152,0.12,0.12,152,3,NULL);

/*Table structure for table `t_withdraw_tax` */

DROP TABLE IF EXISTS `t_withdraw_tax`;

CREATE TABLE `t_withdraw_tax` (
  `id` int(15) unsigned NOT NULL AUTO_INCREMENT,
  `action_date` datetime NOT NULL,
  `wallet_id` int(15) unsigned NOT NULL,
  `cashier_cash_box_id` int(15) unsigned NOT NULL,
  `total_tax` double(15,2) unsigned NOT NULL,
  `total_tax_price` double(15,2) unsigned NOT NULL,
  `process_tax_id` int(15) unsigned NOT NULL,
  `exchange_tax_id` int(15) unsigned DEFAULT NULL,
  `wallet_withdraw_tax_id` int(15) unsigned NOT NULL,
  `is_paid` int(1) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_t_withdraw_taxcbid` (`cashier_cash_box_id`),
  KEY `FK_t_withdraw_taxe` (`exchange_tax_id`),
  KEY `FK_t_withdraw_taxp` (`process_tax_id`),
  KEY `FK_t_withdraw_taxw` (`wallet_withdraw_tax_id`),
  CONSTRAINT `FK_t_withdraw_taxcbid` FOREIGN KEY (`cashier_cash_box_id`) REFERENCES `cb_cashier_cash_box` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `FK_t_withdraw_taxe` FOREIGN KEY (`exchange_tax_id`) REFERENCES `t_withdraw_exchange_tax` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_t_withdraw_taxp` FOREIGN KEY (`process_tax_id`) REFERENCES `t_withdraw_process_tax` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_t_withdraw_taxw` FOREIGN KEY (`wallet_withdraw_tax_id`) REFERENCES `t_wallet_withdraw_tax` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `t_withdraw_tax` */

insert  into `t_withdraw_tax`(`id`,`action_date`,`wallet_id`,`cashier_cash_box_id`,`total_tax`,`total_tax_price`,`process_tax_id`,`exchange_tax_id`,`wallet_withdraw_tax_id`,`is_paid`) values 

(4,'2017-02-20 11:26:04',1705,19,0.12,0.12,4,NULL,4,0);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
