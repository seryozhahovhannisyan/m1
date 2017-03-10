/*
SQLyog Enterprise - MySQL GUI v6.56
MySQL - 5.7.15-log : Database - merchant
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

CREATE DATABASE /*!32312 IF NOT EXISTS*/`merchant` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `merchant`;

/*Table structure for table `a_user` */

DROP TABLE IF EXISTS `a_user`;

CREATE TABLE `a_user` (
  `id` int(3) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(64) COLLATE utf8_bin NOT NULL,
  `name` varchar(64) COLLATE utf8_bin NOT NULL,
  `surname` varchar(64) COLLATE utf8_bin NOT NULL,
  `password` varchar(256) COLLATE utf8_bin NOT NULL,
  `email` varchar(64) COLLATE utf8_bin NOT NULL,
  `phone_code` varchar(3) COLLATE utf8_bin NOT NULL,
  `phone` varchar(64) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `c_branch` */

DROP TABLE IF EXISTS `c_branch`;

CREATE TABLE `c_branch` (
  `id` int(15) unsigned NOT NULL AUTO_INCREMENT,
  `company_id` int(15) unsigned NOT NULL,
  `status` int(1) unsigned NOT NULL,
  `name` varchar(64) COLLATE utf8_bin NOT NULL,
  `address` varchar(128) COLLATE utf8_bin NOT NULL,
  `region` int(11) unsigned DEFAULT NULL,
  `city` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `street` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `zip` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  `email` varchar(64) COLLATE utf8_bin NOT NULL,
  `phone_code` varchar(8) COLLATE utf8_bin NOT NULL,
  `phone` varchar(32) COLLATE utf8_bin NOT NULL,
  `policy_phone_code` varchar(8) COLLATE utf8_bin NOT NULL,
  `policy_phone` varchar(32) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_c_branchc` (`company_id`),
  KEY `FK_c_branchss` (`status`),
  KEY `FK_c_branch` (`region`),
  CONSTRAINT `FK_c_branch` FOREIGN KEY (`region`) REFERENCES `g_country_region_lcp` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_c_branchc` FOREIGN KEY (`company_id`) REFERENCES `c_company` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_c_branchss` FOREIGN KEY (`status`) REFERENCES `status_lcp` (`key`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4493 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `c_cashier` */

DROP TABLE IF EXISTS `c_cashier`;

CREATE TABLE `c_cashier` (
  `id` int(15) unsigned NOT NULL AUTO_INCREMENT,
  `company_id` int(15) unsigned NOT NULL,
  `branch_id` int(15) unsigned NOT NULL,
  `role_id` int(15) unsigned NOT NULL,
  `privilege` int(2) unsigned NOT NULL,
  `head_cashier_id` int(15) unsigned DEFAULT NULL,
  `name` varchar(50) COLLATE utf8_bin NOT NULL,
  `surname` varchar(50) COLLATE utf8_bin NOT NULL,
  `phone_code` varchar(5) COLLATE utf8_bin DEFAULT NULL,
  `phone` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `email` varchar(50) COLLATE utf8_bin NOT NULL DEFAULT '',
  `password` varchar(256) COLLATE utf8_bin NOT NULL,
  `status` int(1) unsigned NOT NULL,
  `online_status` int(1) unsigned DEFAULT NULL,
  `registered_at` date NOT NULL,
  `activated_at` date DEFAULT NULL,
  `verification_code` varchar(7) COLLATE utf8_bin DEFAULT 'poxel',
  `reset_password_token` varchar(256) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `NewIndex1` (`email`),
  UNIQUE KEY `phone` (`phone`),
  KEY `FK_user_status` (`status`),
  KEY `FK_user_pi` (`activated_at`),
  KEY `FK_c_cashierc` (`company_id`),
  KEY `FK_c_cashierb` (`branch_id`),
  KEY `FK_c_cashiero` (`online_status`),
  KEY `FK_c_cashier_h` (`head_cashier_id`),
  KEY `FK_c_cashierp` (`privilege`),
  KEY `FK_c_cashierr` (`role_id`),
  CONSTRAINT `FK_c_cashier` FOREIGN KEY (`status`) REFERENCES `status_lcp` (`key`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_c_cashier_h` FOREIGN KEY (`head_cashier_id`) REFERENCES `c_cashier` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_c_cashierb` FOREIGN KEY (`branch_id`) REFERENCES `c_branch` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_c_cashierc` FOREIGN KEY (`company_id`) REFERENCES `c_company` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_c_cashiero` FOREIGN KEY (`online_status`) REFERENCES `u_online_status_lcp` (`key`),
  CONSTRAINT `FK_c_cashierp` FOREIGN KEY (`privilege`) REFERENCES `c_privilege_lcp` (`id`),
  CONSTRAINT `FK_c_cashierr` FOREIGN KEY (`role_id`) REFERENCES `c_role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4493 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `c_company` */

DROP TABLE IF EXISTS `c_company`;

CREATE TABLE `c_company` (
  `id` int(15) unsigned NOT NULL AUTO_INCREMENT,
  `request_id` int(15) unsigned NOT NULL,
  `response_id` int(15) unsigned NOT NULL,
  `status` int(1) unsigned NOT NULL,
  `tsm_company_id` int(15) unsigned DEFAULT NULL,
  `name` varchar(64) COLLATE utf8_bin NOT NULL,
  `address` varchar(128) COLLATE utf8_bin NOT NULL,
  `country` int(11) unsigned DEFAULT NULL,
  `region` int(11) unsigned DEFAULT NULL,
  `city` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `street` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `zip` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  `email` varchar(64) COLLATE utf8_bin NOT NULL,
  `phone_code` varchar(8) COLLATE utf8_bin NOT NULL,
  `phone` varchar(32) COLLATE utf8_bin NOT NULL,
  `policy_phone_code` varchar(8) COLLATE utf8_bin DEFAULT NULL,
  `policy_phone` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `secret_key` varchar(256) COLLATE utf8_bin NOT NULL,
  `client_key` varchar(256) COLLATE utf8_bin NOT NULL,
  `created_date` datetime DEFAULT NULL,
  `expired_date` datetime DEFAULT NULL,
  `allowed_remote_addresses` varchar(256) COLLATE utf8_bin DEFAULT NULL,
  `allowed_partition_values` varchar(256) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  UNIQUE KEY `name_un` (`name`),
  KEY `FK_c_company_r` (`request_id`),
  KEY `FK_c_company_c` (`country`),
  KEY `FK_c_company_s` (`region`),
  KEY `FK_c_company_ss` (`status`),
  KEY `NewIndex1` (`name`),
  KEY `FK_c_companyrp` (`response_id`),
  CONSTRAINT `FK_c_company_c` FOREIGN KEY (`country`) REFERENCES `g_country_lcp` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_c_company_r` FOREIGN KEY (`request_id`) REFERENCES `c_company_form_request` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_c_company_s` FOREIGN KEY (`region`) REFERENCES `g_country_region_lcp` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_c_company_ss` FOREIGN KEY (`status`) REFERENCES `status_lcp` (`key`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_c_companyrp` FOREIGN KEY (`response_id`) REFERENCES `c_company_form_response` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `c_company_form_request` */

DROP TABLE IF EXISTS `c_company_form_request`;

CREATE TABLE `c_company_form_request` (
  `id` int(15) unsigned NOT NULL AUTO_INCREMENT,
  `tsm_company_id` int(15) unsigned DEFAULT NULL,
  `company_name` varchar(64) COLLATE utf8_bin NOT NULL,
  `company_address` varchar(128) COLLATE utf8_bin NOT NULL,
  `company_email` varchar(64) COLLATE utf8_bin NOT NULL,
  `company_phone_code` varchar(8) COLLATE utf8_bin NOT NULL,
  `company_phone` varchar(32) COLLATE utf8_bin NOT NULL,
  `count_of_branches` int(8) unsigned NOT NULL,
  `count_of_workers` int(8) unsigned NOT NULL,
  `contact_name` varchar(64) COLLATE utf8_bin NOT NULL,
  `contact_last_name` varchar(64) COLLATE utf8_bin NOT NULL,
  `contact_email` varchar(64) COLLATE utf8_bin NOT NULL,
  `contact_phone_code` varchar(8) COLLATE utf8_bin NOT NULL,
  `contact_phone` varchar(32) COLLATE utf8_bin NOT NULL,
  `message` text COLLATE utf8_bin,
  `status` int(1) unsigned NOT NULL,
  `requested_at` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  UNIQUE KEY `NewIndex1` (`company_name`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `c_company_form_response` */

DROP TABLE IF EXISTS `c_company_form_response`;

CREATE TABLE `c_company_form_response` (
  `id` int(15) unsigned NOT NULL AUTO_INCREMENT,
  `u_id` int(15) unsigned NOT NULL,
  `title` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `message` text CHARACTER SET utf8 COLLATE utf8_bin,
  `allowed_remote_addresses` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `allowed_partition_values` varchar(256) DEFAULT NULL,
  `status` int(1) unsigned NOT NULL,
  `respnse_at` datetime NOT NULL,
  `agreement_document_id` int(15) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_c_company_form_response` (`agreement_document_id`),
  KEY `FK_c_company_form_response_u` (`u_id`),
  CONSTRAINT `FK_c_company_form_response` FOREIGN KEY (`agreement_document_id`) REFERENCES `c_data` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_c_company_form_response_u` FOREIGN KEY (`u_id`) REFERENCES `a_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8;

/*Table structure for table `c_data` */

DROP TABLE IF EXISTS `c_data`;

CREATE TABLE `c_data` (
  `id` int(15) unsigned NOT NULL AUTO_INCREMENT,
  `file_name` varchar(256) COLLATE utf8_bin NOT NULL,
  `content_type` varchar(256) COLLATE utf8_bin NOT NULL,
  `size` int(15) unsigned NOT NULL,
  `creation_date` datetime NOT NULL,
  `status` int(1) unsigned NOT NULL,
  `company_id` int(15) unsigned DEFAULT NULL,
  `branch_id` int(15) unsigned DEFAULT NULL,
  `cashier_id` int(15) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_w_data_g` (`company_id`),
  KEY `FK_w_data` (`branch_id`),
  KEY `FK_w_datacr` (`cashier_id`),
  CONSTRAINT `FK_c_data_c` FOREIGN KEY (`company_id`) REFERENCES `c_company` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_c_datab` FOREIGN KEY (`branch_id`) REFERENCES `c_branch` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_c_datacs` FOREIGN KEY (`cashier_id`) REFERENCES `c_cashier` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9081 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `c_privilege_lcp` */

DROP TABLE IF EXISTS `c_privilege_lcp`;

CREATE TABLE `c_privilege_lcp` (
  `id` int(2) unsigned NOT NULL,
  `name` varchar(64) COLLATE utf8_bin NOT NULL,
  `privilege` varchar(64) COLLATE utf8_bin NOT NULL,
  `comment` varchar(128) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `c_role` */

DROP TABLE IF EXISTS `c_role`;

CREATE TABLE `c_role` (
  `id` int(15) unsigned NOT NULL AUTO_INCREMENT,
  `company_id` int(15) unsigned NOT NULL,
  `name` varchar(50) COLLATE utf8_bin NOT NULL,
  `description` text COLLATE utf8_bin,
  `transaction_min` double(15,2) NOT NULL,
  `transaction_max` double(15,2) NOT NULL,
  `is_exchange_allowed` tinyint(1) unsigned NOT NULL,
  `available_rate_values` varchar(1024) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_c_role` (`company_id`),
  CONSTRAINT `FK_c_role` FOREIGN KEY (`company_id`) REFERENCES `c_company` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `cb_branch_cash_box` */

DROP TABLE IF EXISTS `cb_branch_cash_box`;

CREATE TABLE `cb_branch_cash_box` (
  `id` int(15) unsigned NOT NULL AUTO_INCREMENT,
  `branch_id` int(15) unsigned NOT NULL,
  `balance_provided_by_company` double(15,2) unsigned NOT NULL,
  `balance_current` double(15,2) unsigned NOT NULL,
  `balance_total_provided_for_cashier_cb` double(15,2) unsigned NOT NULL,
  `balance_current_provided_for_cashier_cb` double(15,2) unsigned NOT NULL,
  `balance_returned_from_cashier_cb` double(15,2) unsigned NOT NULL,
  `balance_gathered_tax` double(15,2) unsigned NOT NULL,
  `currency_type` int(3) unsigned NOT NULL,
  `opened_at` datetime NOT NULL,
  `closed_at` datetime DEFAULT NULL,
  `status` int(1) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_cb_branch_cash_box_b` (`branch_id`),
  KEY `FK_cb_branch_cash_boxs` (`status`),
  KEY `FK_cb_branch_cash_boxct` (`currency_type`),
  CONSTRAINT `FK_cb_branch_cash_box_b` FOREIGN KEY (`branch_id`) REFERENCES `c_branch` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_cb_branch_cash_boxct` FOREIGN KEY (`currency_type`) REFERENCES `g_currency_type_lcp` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `FK_cb_branch_cash_boxs` FOREIGN KEY (`status`) REFERENCES `status_lcp` (`key`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4493 DEFAULT CHARSET=utf8;

/*Table structure for table `cb_branch_provider` */

DROP TABLE IF EXISTS `cb_branch_provider`;

CREATE TABLE `cb_branch_provider` (
  `id` int(15) unsigned NOT NULL AUTO_INCREMENT,
  `branch_cash_box_id` int(15) unsigned NOT NULL,
  `company_cash_box_id` int(15) unsigned NOT NULL,
  `balance_provided` double(15,2) unsigned NOT NULL,
  `balance_taken` double(15,2) unsigned DEFAULT NULL,
  `currency_type` int(3) unsigned NOT NULL,
  `provided_by` int(15) unsigned NOT NULL,
  `provided_at` datetime NOT NULL,
  `is_taken` int(1) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `FK_cb_branch_provider` (`company_cash_box_id`),
  KEY `FK_cb_branch_providercb` (`branch_cash_box_id`),
  KEY `FK_cb_branch_providerct` (`currency_type`),
  CONSTRAINT `FK_cb_branch_provider` FOREIGN KEY (`company_cash_box_id`) REFERENCES `cb_company_cash_box` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_cb_branch_providercb` FOREIGN KEY (`branch_cash_box_id`) REFERENCES `cb_branch_cash_box` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_cb_branch_providerct` FOREIGN KEY (`currency_type`) REFERENCES `g_currency_type_lcp` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

/*Table structure for table `cb_branch_taker` */

DROP TABLE IF EXISTS `cb_branch_taker`;

CREATE TABLE `cb_branch_taker` (
  `id` int(15) unsigned NOT NULL AUTO_INCREMENT,
  `branch_cash_box_id` int(15) unsigned NOT NULL,
  `company_cash_box_id` int(15) unsigned NOT NULL,
  `balance_taken` double(15,2) unsigned NOT NULL,
  `currency_type` int(3) unsigned NOT NULL,
  `took_by` int(15) unsigned NOT NULL,
  `took_at` datetime NOT NULL,
  `provider_id` int(15) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_cb_branch_takerbc` (`branch_cash_box_id`),
  KEY `FK_cb_branch_takercb` (`company_cash_box_id`),
  KEY `FK_cb_branch_takerct` (`currency_type`),
  KEY `FK_cb_branch_takerp` (`provider_id`),
  CONSTRAINT `FK_cb_branch_takerbc` FOREIGN KEY (`branch_cash_box_id`) REFERENCES `cb_branch_cash_box` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_cb_branch_takercb` FOREIGN KEY (`company_cash_box_id`) REFERENCES `cb_company_cash_box` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_cb_branch_takerct` FOREIGN KEY (`currency_type`) REFERENCES `g_currency_type_lcp` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `FK_cb_branch_takerp` FOREIGN KEY (`provider_id`) REFERENCES `cb_branch_provider` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `cb_cashier_cash_box` */

DROP TABLE IF EXISTS `cb_cashier_cash_box`;

CREATE TABLE `cb_cashier_cash_box` (
  `id` int(15) unsigned NOT NULL AUTO_INCREMENT,
  `cashier_id` int(15) unsigned NOT NULL,
  `balance_provided_by_branch` double(15,2) unsigned NOT NULL,
  `balance_current` double(15,2) unsigned NOT NULL,
  `balance_gathered_tax` double(15,2) unsigned NOT NULL,
  `currency_type` int(3) unsigned NOT NULL,
  `pending_balance_deposit` double(15,2) unsigned NOT NULL,
  `pending_balance_withdraw` double(15,2) unsigned NOT NULL,
  `pending_tax_amount` double(15,2) unsigned NOT NULL,
  `opened_at` datetime NOT NULL,
  `closed_at` datetime DEFAULT NULL,
  `status` int(1) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_cb_cashier_cash_boxs` (`status`),
  KEY `FK_cb_cashier_cash_boxct` (`currency_type`),
  KEY `FK_cb_cashier_cash_boxc` (`cashier_id`),
  CONSTRAINT `FK_cb_cashier_cash_boxc` FOREIGN KEY (`cashier_id`) REFERENCES `c_cashier` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_cb_cashier_cash_boxct` FOREIGN KEY (`currency_type`) REFERENCES `g_currency_type_lcp` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `FK_cb_cashier_cash_boxs` FOREIGN KEY (`status`) REFERENCES `status_lcp` (`key`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4492 DEFAULT CHARSET=utf8;

/*Table structure for table `cb_cashier_provider` */

DROP TABLE IF EXISTS `cb_cashier_provider`;

CREATE TABLE `cb_cashier_provider` (
  `id` int(15) unsigned NOT NULL AUTO_INCREMENT,
  `cashir_cash_box_id` int(15) unsigned NOT NULL,
  `branch_cash_box_id` int(15) unsigned NOT NULL,
  `balance_provided` double(15,2) unsigned NOT NULL,
  `currency_type` int(3) unsigned NOT NULL,
  `provided_at` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_cb_cashier_providerct` (`currency_type`),
  KEY `FK_cb_cashier_providercb` (`cashir_cash_box_id`),
  KEY `FK_cb_cashier_provider` (`branch_cash_box_id`),
  CONSTRAINT `FK_cb_cashier_provider` FOREIGN KEY (`branch_cash_box_id`) REFERENCES `cb_branch_cash_box` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_cb_cashier_providercb` FOREIGN KEY (`cashir_cash_box_id`) REFERENCES `cb_cashier_cash_box` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_cb_cashier_providerct` FOREIGN KEY (`currency_type`) REFERENCES `g_currency_type_lcp` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `cb_cashier_taker` */

DROP TABLE IF EXISTS `cb_cashier_taker`;

CREATE TABLE `cb_cashier_taker` (
  `id` int(15) unsigned NOT NULL AUTO_INCREMENT,
  `cashir_cash_box_id` int(15) unsigned NOT NULL,
  `branch_cash_box_id` int(15) unsigned NOT NULL,
  `balance_taken` double(15,2) unsigned NOT NULL,
  `currency_type` int(3) unsigned NOT NULL,
  `took_at` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_cb_cashier_takerct` (`currency_type`),
  KEY `FK_cb_cashier_takerbc` (`branch_cash_box_id`),
  KEY `FK_cb_cashier_takercb` (`cashir_cash_box_id`),
  CONSTRAINT `FK_cb_cashier_takerbc` FOREIGN KEY (`branch_cash_box_id`) REFERENCES `cb_branch_cash_box` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_cb_cashier_takercb` FOREIGN KEY (`cashir_cash_box_id`) REFERENCES `cb_cashier_cash_box` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_cb_cashier_takerct` FOREIGN KEY (`currency_type`) REFERENCES `g_currency_type_lcp` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `cb_company_cash_box` */

DROP TABLE IF EXISTS `cb_company_cash_box`;

CREATE TABLE `cb_company_cash_box` (
  `id` int(15) unsigned NOT NULL AUTO_INCREMENT,
  `company_id` int(15) unsigned NOT NULL,
  `balance_provided_by_merchant` double(15,2) unsigned NOT NULL,
  `balance_current` double(15,2) unsigned NOT NULL,
  `accepted_overpayment` double(15,2) unsigned NOT NULL,
  `overpayment` double(15,2) unsigned DEFAULT NULL,
  `balance_total_provided_for_branch_cb` double(15,2) unsigned DEFAULT NULL,
  `balance_current_provided_for_branch_cb` double(15,2) unsigned DEFAULT NULL,
  `balance_returned_from_branch_cb` double(15,2) unsigned DEFAULT NULL,
  `balance_gathered_tax` double(15,2) unsigned DEFAULT NULL,
  `currency_type` int(3) unsigned NOT NULL,
  `opened_at` datetime NOT NULL,
  `closed_at` datetime DEFAULT NULL,
  `status` int(1) unsigned NOT NULL,
  `available_rates` varchar(1024) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `deposit_fee_percent` double(15,2) unsigned NOT NULL,
  `deposit_min_fee` float(15,2) unsigned NOT NULL,
  `deposit_max_fee` float(15,2) unsigned NOT NULL,
  `withdraw_fee_percent` float(15,2) unsigned NOT NULL,
  `withdraw_min_fee` float(15,2) unsigned NOT NULL,
  `withdraw_max_fee` float(15,2) unsigned NOT NULL,
  `exchange_deposit_fee_percent` float(15,2) unsigned NOT NULL,
  `exchange_deposit_min_fee` float(15,2) unsigned NOT NULL,
  `exchange_deposit_max_fee` float(15,2) unsigned NOT NULL,
  `exchange_withdraw_fee_percent` float(15,2) unsigned NOT NULL,
  `exchange_withdraw_min_fee` float(15,2) unsigned NOT NULL,
  `exchange_withdraw_max_fee` float(15,2) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_cb_company_cash_box` (`company_id`),
  KEY `FK_cb_company_cash_boxct` (`currency_type`),
  KEY `FK_cb_company_cash_boxs` (`status`),
  CONSTRAINT `FK_cb_company_cash_box` FOREIGN KEY (`company_id`) REFERENCES `c_company` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_cb_company_cash_boxct` FOREIGN KEY (`currency_type`) REFERENCES `g_currency_type_lcp` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `FK_cb_company_cash_boxs` FOREIGN KEY (`status`) REFERENCES `status_lcp` (`key`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8;

/*Table structure for table `cb_company_provider` */

DROP TABLE IF EXISTS `cb_company_provider`;

CREATE TABLE `cb_company_provider` (
  `id` int(15) unsigned NOT NULL AUTO_INCREMENT,
  `company_cash_box_id` int(15) unsigned NOT NULL,
  `company_form_response_id` int(15) unsigned DEFAULT NULL,
  `balance_provided` double(15,2) unsigned NOT NULL,
  `currency_type` int(3) unsigned NOT NULL,
  `provided_at` datetime NOT NULL,
  `offer_id` int(15) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_cb_company_providerct` (`currency_type`),
  KEY `FK_cb_company_provider_rp` (`company_form_response_id`),
  KEY `FK_cb_company_providercb` (`company_cash_box_id`),
  KEY `FK_cb_company_providero` (`offer_id`),
  CONSTRAINT `FK_cb_company_provider_rp` FOREIGN KEY (`company_form_response_id`) REFERENCES `c_company_form_response` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_cb_company_providercb` FOREIGN KEY (`company_cash_box_id`) REFERENCES `cb_company_cash_box` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_cb_company_providerct` FOREIGN KEY (`currency_type`) REFERENCES `g_currency_type_lcp` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `FK_cb_company_providero` FOREIGN KEY (`offer_id`) REFERENCES `cb_company_provider_offer` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `cb_company_provider_offer` */

DROP TABLE IF EXISTS `cb_company_provider_offer`;

CREATE TABLE `cb_company_provider_offer` (
  `id` int(15) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `reason` text NOT NULL,
  `amount` double(15,2) unsigned NOT NULL,
  `currency_type` int(3) unsigned NOT NULL,
  `offered_at` datetime NOT NULL,
  `status` int(1) unsigned NOT NULL,
  `secret_key` varchar(264) NOT NULL,
  `client_key` varchar(264) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `cb_company_taker` */

DROP TABLE IF EXISTS `cb_company_taker`;

CREATE TABLE `cb_company_taker` (
  `id` int(15) unsigned NOT NULL AUTO_INCREMENT,
  `company_cash_box_id` int(15) unsigned NOT NULL,
  `company_form_response_id` int(15) unsigned NOT NULL,
  `balance_taken` double(15,2) unsigned NOT NULL,
  `currency_type` int(3) unsigned NOT NULL,
  `took_at` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_cb_company_takerct` (`currency_type`),
  KEY `FK_cb_company_takerrp` (`company_form_response_id`),
  KEY `FK_cb_company_taker` (`company_cash_box_id`),
  CONSTRAINT `FK_cb_company_taker` FOREIGN KEY (`company_cash_box_id`) REFERENCES `cb_company_cash_box` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_cb_company_takerct` FOREIGN KEY (`currency_type`) REFERENCES `g_currency_type_lcp` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `FK_cb_company_takerrp` FOREIGN KEY (`company_form_response_id`) REFERENCES `c_company_form_response` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `g_country_lcp` */

DROP TABLE IF EXISTS `g_country_lcp`;

CREATE TABLE `g_country_lcp` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `abr` varchar(3) NOT NULL,
  `phone_code` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Table structure for table `g_country_region_lcp` */

DROP TABLE IF EXISTS `g_country_region_lcp`;

CREATE TABLE `g_country_region_lcp` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `abr` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `country_id` int(11) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_g_country_regionc_lcp` (`country_id`),
  CONSTRAINT `FK_g_country_regionc_lcp` FOREIGN KEY (`country_id`) REFERENCES `g_country_lcp` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=latin1 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC;

/*Table structure for table `g_currency_type_lcp` */

DROP TABLE IF EXISTS `g_currency_type_lcp`;

CREATE TABLE `g_currency_type_lcp` (
  `id` int(3) unsigned NOT NULL,
  `code` varchar(3) COLLATE utf8_bin NOT NULL,
  `name` varchar(50) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `NewIndex1` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `g_language_lcp` */

DROP TABLE IF EXISTS `g_language_lcp`;

CREATE TABLE `g_language_lcp` (
  `value` int(2) unsigned NOT NULL,
  `key` varchar(32) COLLATE utf8_bin NOT NULL,
  `title` varchar(32) COLLATE utf8_bin NOT NULL,
  `locale` varchar(32) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`value`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `m_logger` */

DROP TABLE IF EXISTS `m_logger`;

CREATE TABLE `m_logger` (
  `id` int(15) unsigned NOT NULL AUTO_INCREMENT,
  `level` enum('info','fatal','error','warn','info','debug','trace','all') COLLATE utf8_bin NOT NULL DEFAULT 'all',
  `class_name` text COLLATE utf8_bin,
  `method_name` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `log_action` enum('create','read','update','delete','insert','util','attach') COLLATE utf8_bin NOT NULL,
  `message` text COLLATE utf8_bin,
  `date` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `status_lcp` */

DROP TABLE IF EXISTS `status_lcp`;

CREATE TABLE `status_lcp` (
  `key` int(1) unsigned NOT NULL,
  `status` varchar(50) NOT NULL,
  PRIMARY KEY (`key`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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

/*Table structure for table `t_rational_duration_lcp` */

DROP TABLE IF EXISTS `t_rational_duration_lcp`;

CREATE TABLE `t_rational_duration_lcp` (
  `id` int(1) unsigned NOT NULL,
  `type` varchar(32) COLLATE utf8_bin NOT NULL,
  `duration` varchar(32) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `t_state_lcp` */

DROP TABLE IF EXISTS `t_state_lcp`;

CREATE TABLE `t_state_lcp` (
  `id` int(1) unsigned NOT NULL,
  `state` varchar(50) COLLATE utf8_bin NOT NULL,
  `description` varchar(64) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `t_tax_type_lcp` */

DROP TABLE IF EXISTS `t_tax_type_lcp`;

CREATE TABLE `t_tax_type_lcp` (
  `id` int(1) unsigned NOT NULL,
  `type` varchar(32) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `t_transfer_ticket` */

DROP TABLE IF EXISTS `t_transfer_ticket`;

CREATE TABLE `t_transfer_ticket` (
  `id` int(15) unsigned NOT NULL AUTO_INCREMENT,
  `item_id` int(15) unsigned NOT NULL,
  `wallet_id` int(15) unsigned NOT NULL,
  `tsm_company_id` int(15) unsigned NOT NULL,
  `name` varchar(1024) COLLATE utf8_bin NOT NULL,
  `description` text COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `t_transfer_transaction` */

DROP TABLE IF EXISTS `t_transfer_transaction`;

CREATE TABLE `t_transfer_transaction` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `transfer_ticket_id` int(15) unsigned NOT NULL,
  `wallet_result_id` int(15) unsigned DEFAULT NULL,
  `company_cash_box_id` int(15) unsigned NOT NULL,
  `wallet_id` int(15) unsigned NOT NULL,
  `action_date` datetime DEFAULT NULL,
  `transfer_amount` varchar(512) COLLATE utf8_bin NOT NULL,
  `transfer_amount_ct` int(3) unsigned NOT NULL,
  `is_encoded` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_w_transfer_ticket_id` (`transfer_ticket_id`),
  KEY `FK_w_transfer_wallet_result_id` (`wallet_result_id`),
  KEY `FK_w_transfer_company_cash_box_id` (`company_cash_box_id`),
  CONSTRAINT `FK_w_transfer_company_cash_box_id` FOREIGN KEY (`company_cash_box_id`) REFERENCES `cb_company_cash_box` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_w_transfer_ticket_id` FOREIGN KEY (`transfer_ticket_id`) REFERENCES `t_transfer_ticket` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_w_transfer_wallet_result_id` FOREIGN KEY (`wallet_result_id`) REFERENCES `t_transfer_wallet_result` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `t_transfer_wallet_result` */

DROP TABLE IF EXISTS `t_transfer_wallet_result`;

CREATE TABLE `t_transfer_wallet_result` (
  `id` int(15) unsigned NOT NULL AUTO_INCREMENT,
  `transaction_id` int(15) unsigned NOT NULL,
  `action_date` datetime DEFAULT NULL,
  `wallet_id` int(15) unsigned NOT NULL,
  `wallet_ct` int(3) unsigned NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

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

/*Table structure for table `t_wallet_deposit_tax` */

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `u_online_status_lcp` */

DROP TABLE IF EXISTS `u_online_status_lcp`;

CREATE TABLE `u_online_status_lcp` (
  `key` int(1) unsigned NOT NULL,
  `status` varchar(50) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
