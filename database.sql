create database front_running
use  front_running

CREATE TABLE [dbo].[AppleCustomerOrder]( [TradeID] [int] NOT NULL, [CustomerID] [int] NOT NULL, [TradeType] [varchar](50) NOT NULL, [SecurityType] [nvarchar](50) NOT NULL, [SecurityName] [nvarchar](50) NOT NULL, [Price] [int] NOT NULL, [Quantity] [float] NOT NULL, [Time] [datetime] NULL, CONSTRAINT [PK2_CustomerOrder] PRIMARY KEY CLUSTERED ( [TradeID] ASC )WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY] )

CREATE TABLE [dbo].[FacebookCustomerOrder]( [TradeID] [int] NOT NULL, [CustomerID] [int] NOT NULL, [TradeType] [varchar](50) NOT NULL, [SecurityType] [nvarchar](50) NOT NULL, [SecurityName] [nvarchar](50) NOT NULL, [Price] [int] NOT NULL, [Quantity] [float] NOT NULL, [Time] [datetime] NULL, CONSTRAINT [PK1_CustomerOrder] PRIMARY KEY CLUSTERED ( [TradeID] ASC )WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY] )

CREATE TABLE [dbo].[WalmartCustomerOrder]( [TradeID] [int] NOT NULL, [CustomerID] [int] NOT NULL, [TradeType] [varchar](50) NOT NULL, [SecurityType] [nvarchar](50) NOT NULL, [SecurityName] [nvarchar](50) NOT NULL, [Price] [int] NOT NULL, [Quantity] [float] NOT NULL, [Time] [datetime] NULL, CONSTRAINT [PK3_CustomerOrder] PRIMARY KEY CLUSTERED ( [TradeID] ASC )WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY] )

CREATE TABLE [dbo].[FacebookFirmOrder]( [TradeID] [int] NOT NULL, [TradeType] [varchar](50) NOT NULL, [SecurityType] [varchar](50) NOT NULL, [SecurityName] [varchar](50) NOT NULL, [Price] [int] NOT NULL, [Quantity] [float] NOT NULL, [Time] [datetime] NOT NULL, CONSTRAINT [PK1_FirmOrder] PRIMARY KEY CLUSTERED ( [TradeID] ASC )WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY] )

CREATE TABLE [dbo].[AppleFirmOrder]( [TradeID] [int] NOT NULL, [TradeType] [varchar](50) NOT NULL, [SecurityType] [varchar](50) NOT NULL, [SecurityName] [varchar](50) NOT NULL, [Price] [int] NOT NULL, [Quantity] [float] NOT NULL, [Time] [datetime] NOT NULL, CONSTRAINT [PK2_FirmOrder] PRIMARY KEY CLUSTERED ( [TradeID] ASC )WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY] )

CREATE TABLE [dbo].[WalmartFirmOrder]( [TradeID] [int] NOT NULL, [TradeType] [varchar](50) NOT NULL, [SecurityType] [varchar](50) NOT NULL, [SecurityName] [varchar](50) NOT NULL, [Price] [int] NOT NULL, [Quantity] [float] NOT NULL, [Time] [datetime] NOT NULL, CONSTRAINT [PK3_FirmOrder] PRIMARY KEY CLUSTERED ( [TradeID] ASC )WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY] )

CREATE TABLE [dbo].[AlertTable]( [TradeID] [int] NOT NULL, [CustomerID] [int] NOT NULL, [TradeType] [varchar](50) NOT NULL, [SecurityType] [varchar](50) NOT NULL, [SecurityName] [varchar](50) NOT NULL, [Price] [int] NOT NULL, [Quantity] [float] NOT NULL, [Time] [datetime] NOT NULL, CONSTRAINT [PK_AlertTable] PRIMARY KEY CLUSTERED ( [TradeID] ASC )WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY] )


select * from AppleCustomerOrder