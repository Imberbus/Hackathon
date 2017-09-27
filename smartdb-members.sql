-- phpMyAdmin SQL Dump
-- version 4.2.7
-- http://www.phpmyadmin.net
--
-- Host: localhost:8889
-- Generation Time: Dec 21, 2015 at 10:11 PM
-- Server version: 5.6.17-debug-log
-- PHP Version: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `smartdb-members`
--

-- --------------------------------------------------------

--
-- Table structure for table `facturation`
--

CREATE TABLE IF NOT EXISTS `facturation` (
  `email` varchar(40) NOT NULL,
  `mois` int(2) NOT NULL,
  `annee` int(4) NOT NULL,
  `prix` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `members`
--

CREATE TABLE IF NOT EXISTS `members` (
  `email` varchar(40) NOT NULL,
  `pwd` varchar(8) NOT NULL,
  `vip` int(1) NOT NULL,
  `bdd` varchar(40) NOT NULL,
  `ip` varchar(16) NOT NULL,
  `port` int(8) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



-- --------------------------------------------------------

--
-- Table structure for table `mybdd`
--

CREATE TABLE IF NOT EXISTS `mybdd` (
  `email` varchar(40) NOT NULL,
  `bdd` varchar(40) NOT NULL,
  `uname` varchar(16),
  `pwd` varchar(16)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


-- --------------------------------------------------------

--
-- Table structure for table `container`
--

CREATE TABLE IF NOT EXISTS `container` (
  `email` varchar(40) NOT NULL,
  `ip` varchar(40) NOT NULL,
  `vmid` int(20) NOT NULL,
  `port` int(20) NOT NULL,
  `taille` int(20)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `members`
--

--
-- Indexes for dumped tables
--

--
-- Indexes for table `facturation`
--
ALTER TABLE `facturation`
 ADD PRIMARY KEY (`email`,`mois`,`annee`);

--
-- Indexes for table `members`
--
ALTER TABLE `members`
 ADD PRIMARY KEY (`email`,`bdd`);

--
-- Indexes for table `mybdd`
--
ALTER TABLE `mybdd`
 ADD PRIMARY KEY (`email`);

--
-- Indexes for table `container`
--
ALTER TABLE `container`
 ADD PRIMARY KEY (`email`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
