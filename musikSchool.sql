USE [master]
GO
/****** Object:  Database [musicSchool]    Script Date: 30.01.2023 23:24:39 ******/
CREATE DATABASE [musicSchool]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'testDatenbank2', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.SQLEXPRESS\MSSQL\DATA\testDatenbank2.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'testDatenbank2_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.SQLEXPRESS\MSSQL\DATA\testDatenbank2_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [musicSchool] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [musicSchool].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [musicSchool] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [musicSchool] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [musicSchool] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [musicSchool] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [musicSchool] SET ARITHABORT OFF 
GO
ALTER DATABASE [musicSchool] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [musicSchool] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [musicSchool] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [musicSchool] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [musicSchool] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [musicSchool] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [musicSchool] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [musicSchool] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [musicSchool] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [musicSchool] SET  DISABLE_BROKER 
GO
ALTER DATABASE [musicSchool] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [musicSchool] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [musicSchool] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [musicSchool] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [musicSchool] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [musicSchool] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [musicSchool] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [musicSchool] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [musicSchool] SET  MULTI_USER 
GO
ALTER DATABASE [musicSchool] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [musicSchool] SET DB_CHAINING OFF 
GO
ALTER DATABASE [musicSchool] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [musicSchool] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [musicSchool] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [musicSchool] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
ALTER DATABASE [musicSchool] SET QUERY_STORE = OFF
GO
USE [musicSchool]
GO
/****** Object:  Table [dbo].[instrument]    Script Date: 30.01.2023 23:24:40 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[instrument](
	[InstrumentID] [numeric](18, 0) IDENTITY(1,1) NOT NULL,
	[instrumentName] [varchar](256) NULL,
PRIMARY KEY CLUSTERED 
(
	[InstrumentID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[lehrer]    Script Date: 30.01.2023 23:24:40 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[lehrer](
	[lehrerID] [numeric](18, 0) IDENTITY(1,1) NOT NULL,
	[lehrerName] [varchar](256) NULL,
PRIMARY KEY CLUSTERED 
(
	[lehrerID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[lehrer_x_instrument]    Script Date: 30.01.2023 23:24:40 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[lehrer_x_instrument](
	[id] [numeric](18, 0) IDENTITY(1,1) NOT NULL,
	[lehrerID] [numeric](18, 0) NULL,
	[instrumentID] [numeric](18, 0) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[raum]    Script Date: 30.01.2023 23:24:40 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[raum](
	[raumID] [numeric](18, 0) IDENTITY(1,1) NOT NULL,
	[raumName] [varchar](256) NULL,
PRIMARY KEY CLUSTERED 
(
	[raumID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[schueler]    Script Date: 30.01.2023 23:24:40 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[schueler](
	[id] [numeric](18, 0) IDENTITY(1,1) NOT NULL,
	[name] [varchar](256) NULL,
	[geburtsjahr] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[stunde]    Script Date: 30.01.2023 23:24:40 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[stunde](
	[stundenID] [numeric](18, 0) NOT NULL,
	[anfang] [time](7) NULL,
	[ende] [time](7) NULL,
PRIMARY KEY CLUSTERED 
(
	[stundenID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tag]    Script Date: 30.01.2023 23:24:40 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tag](
	[tageID] [numeric](18, 0) NOT NULL,
	[tageName] [varchar](256) NULL,
PRIMARY KEY CLUSTERED 
(
	[tageID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[unterrichtsStunde]    Script Date: 30.01.2023 23:24:40 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[unterrichtsStunde](
	[id] [numeric](18, 0) IDENTITY(1,1) NOT NULL,
	[schuelerID] [numeric](18, 0) NULL,
	[instrumentID] [numeric](18, 0) NULL,
	[lehrerID] [numeric](18, 0) NULL,
	[wochentagID] [numeric](18, 0) NULL,
	[stundeID] [numeric](18, 0) NULL,
	[raumID] [numeric](18, 0) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[lehrer_x_instrument]  WITH CHECK ADD  CONSTRAINT [FK__lehrer_x___instr__5441852A] FOREIGN KEY([instrumentID])
REFERENCES [dbo].[instrument] ([InstrumentID])
GO
ALTER TABLE [dbo].[lehrer_x_instrument] CHECK CONSTRAINT [FK__lehrer_x___instr__5441852A]
GO
ALTER TABLE [dbo].[lehrer_x_instrument]  WITH CHECK ADD  CONSTRAINT [FK__lehrer_x___lehre__534D60F1] FOREIGN KEY([lehrerID])
REFERENCES [dbo].[lehrer] ([lehrerID])
GO
ALTER TABLE [dbo].[lehrer_x_instrument] CHECK CONSTRAINT [FK__lehrer_x___lehre__534D60F1]
GO
ALTER TABLE [dbo].[unterrichtsStunde]  WITH CHECK ADD FOREIGN KEY([instrumentID])
REFERENCES [dbo].[instrument] ([InstrumentID])
GO
ALTER TABLE [dbo].[unterrichtsStunde]  WITH CHECK ADD FOREIGN KEY([lehrerID])
REFERENCES [dbo].[lehrer] ([lehrerID])
GO
ALTER TABLE [dbo].[unterrichtsStunde]  WITH CHECK ADD FOREIGN KEY([schuelerID])
REFERENCES [dbo].[schueler] ([id])
GO
ALTER TABLE [dbo].[unterrichtsStunde]  WITH CHECK ADD FOREIGN KEY([stundeID])
REFERENCES [dbo].[stunde] ([stundenID])
GO
ALTER TABLE [dbo].[unterrichtsStunde]  WITH CHECK ADD FOREIGN KEY([raumID])
REFERENCES [dbo].[raum] ([raumID])
GO
ALTER TABLE [dbo].[unterrichtsStunde]  WITH CHECK ADD FOREIGN KEY([wochentagID])
REFERENCES [dbo].[tag] ([tageID])
GO
USE [master]
GO
ALTER DATABASE [musicSchool] SET  READ_WRITE 
GO
