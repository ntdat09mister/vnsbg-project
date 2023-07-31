# vnsbg-project
create db : docker run -e 'ACCEPT_EULA=Y' -e 'SA_PASSWORD=123456a@' -e 'MSSQL_PID=Express' --name sql-server-db -p 1433:1433 -d mcr.microsoft.com/mssql/server:latest
DB: SQL SERVER
DDL Table
-- master.dbo.cart definition

-- Drop table

-- DROP TABLE master.dbo.cart;

CREATE TABLE cart (
	id int IDENTITY(0,1) NOT NULL,
	customer_id varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	girl_id varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	price varchar(4) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	sales_date date NULL,
	status varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	vote_customer float NULL,
	CONSTRAINT customer_PK PRIMARY KEY (id)
);

-- master.dbo.customers definition

-- Drop table

-- DROP TABLE master.dbo.customers;

CREATE TABLE customers (
	id int NOT NULL,
	first_name nvarchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	last_name nvarchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	[rank] int NULL,
	CONSTRAINT NewTable_PK PRIMARY KEY (id)
);

-- master.dbo.girl_description definition

-- Drop table

-- DROP TABLE master.dbo.girl_description;

CREATE TABLE girl_description (
	id int NOT NULL,
	girl_id varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	description varchar(1000) COLLATE SQL_Latin1_General_CP1_CI_AS DEFAULT NULL NULL,
	CONSTRAINT PK__girl_des__3213E83F2CD47D2C PRIMARY KEY (id)
);

-- master.dbo.girl_detail definition

-- Drop table

-- DROP TABLE master.dbo.girl_detail;

CREATE TABLE girl_detail (
	id int NOT NULL,
	marial_status int NULL,
	nationality int NULL,
	height float NULL,
	weight float NULL,
	round_ms_1 float NULL,
	round_ms_2 float NULL,
	round_ms_3 float NULL,
	girl_id varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	rating_vote float NULL,
	CONSTRAINT girl_detail_PK PRIMARY KEY (id)
);

-- master.dbo.girl_detail_image definition

-- Drop table

-- DROP TABLE master.dbo.girl_detail_image;

CREATE TABLE girl_detail_image (
	id int NOT NULL,
	girl_id varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS DEFAULT NULL NULL,
	url varchar(500) COLLATE SQL_Latin1_General_CP1_CI_AS DEFAULT NULL NULL,
	CONSTRAINT PK__girl_det__3213E83FD0EB2F5C PRIMARY KEY (id)
);

-- master.dbo.girls definition

-- Drop table

-- DROP TABLE master.dbo.girls;

CREATE TABLE girls (
	id int NOT NULL,
	description varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS DEFAULT NULL NULL,
	price varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS DEFAULT NULL NULL,
	url varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS DEFAULT NULL NULL,
	reviews int NULL,
	name varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	CONSTRAINT PK__girl__3213E83F3AEEF982 PRIMARY KEY (id)
);

-- master.dbo.kpis definition

-- Drop table

-- DROP TABLE master.dbo.kpis;

CREATE TABLE kpis (
	id int IDENTITY(0,1) NOT NULL,
	order_code varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	price varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	sales_date date NULL
);

-- master.dbo.like_baby definition

-- Drop table

-- DROP TABLE master.dbo.like_baby;

CREATE TABLE like_baby (
	id int IDENTITY(0,1) NOT NULL,
	girl_id varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	customer_id int NULL,
	like_date date NULL,
	CONSTRAINT like_babies_PK PRIMARY KEY (id)
);

-- master.dbo.order_status definition

-- Drop table

-- DROP TABLE master.dbo.order_status;

CREATE TABLE order_status (
	id int IDENTITY(0,1) NOT NULL,
	status nvarchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	CONSTRAINT order_status_PK PRIMARY KEY (id)
);

-- master.dbo.promo_details definition

-- Drop table

-- DROP TABLE master.dbo.promo_details;

CREATE TABLE promo_details (
	id int NOT NULL,
	promo_detail nvarchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS NULL
);

-- master.dbo.user_detail definition

-- Drop table

-- DROP TABLE master.dbo.user_detail;

CREATE TABLE user_detail (
	user_id int NOT NULL,
	mail varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	phone varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	avatar varchar(500) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	CONSTRAINT user_detail_PK PRIMARY KEY (user_id)
);

-- master.dbo.users definition

-- Drop table

-- DROP TABLE master.dbo.users;

CREATE TABLE users (
	id int IDENTITY(0,1) NOT NULL,
	customer_id int NULL,
	username varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	password varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	full_name varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	CONSTRAINT user_PK PRIMARY KEY (id)
);

--------------------------------------------------------------------
--------------------------------------------------------------------
CREATE PROCEDURE dbo.AddNewBabies
@description varchar(max),
@price varchar(30),
@url varchar(max),
@marial_status int,
@nationality int,
@height float,
@weight float,
@round1 float,
@round2 float,
@round3 float
AS
SET NOCOUNT ON
DECLARE @voteCustomer varchar(50), @trancount int, @girlId_lastest int, @girl_id int, @girl_detail_latest int, @girl_detail_id int
SET @trancount = @@trancount;
SET @voteCustomer = null
BEGIN TRY
	   IF @trancount = 0
      BEGIN TRANSACTION
      ELSE
      SAVE TRANSACTION AddNewBabies;
      SELECT TOP 1 @girlId_lastest = g.id FROM girls g ORDER BY g.id DESC
      SET @girl_id = @girlId_lastest + 1
      INSERT INTO girls (id, description, price, url) values (@girl_id, @description, @price, @url)
      SELECT TOP 1 @girl_detail_latest = g.id FROM girls g ORDER BY g.id DESC
      SET @girl_detail_id = @girl_detail_latest + 1
      INSERT INTO girl_detail (id, marial_status, nationality, height, weight, round_ms_1, round_ms_2, round_ms_3, girl_id)
      VALUES (@girl_detail_id, @marial_status, @nationality, @height, @weight, @round1, @round2, @round3, @girl_id)
      lbexit:
      IF @trancount = 0
      COMMIT;
END TRY
BEGIN CATCH
    DECLARE @error int,
            @message varchar(4000),
            @xstate int;

    SELECT
      @error = ERROR_NUMBER(),
      @message = ERROR_MESSAGE(),
      @xstate = XACT_STATE();

    IF @xstate = -1
      ROLLBACK;
    IF @xstate = 1 AND @trancount = 0
      ROLLBACK
    IF @xstate = 1 AND @trancount > 0
      ROLLBACK TRANSACTION AddNewBabies;
END CATCH

----------------------------------------
CREATE PROCEDURE dbo.AddToCart
@customerId VARCHAR(50),
@girlId VARCHAR(50),
@price VARCHAR(50),
@saleDate VARCHAR(50),
@status VARCHAR(50)
AS
SET NOCOUNT ON
DECLARE @voteCustomer varchar(50), @trancount int;
SET @trancount = @@trancount;
SET @voteCustomer = null
BEGIN TRY
	   IF @trancount = 0
      BEGIN TRANSACTION
      ELSE
      SAVE TRANSACTION AddToCart;
     INSERT INTO cart (customer_id, girl_id, price, sales_date, status, vote_customer) values (@customerId,@girlId, @price, @saleDate, @Status, @voteCustomer)
         lbexit:
      IF @trancount = 0
      COMMIT;
END TRY
BEGIN CATCH
    DECLARE @error int,
            @message varchar(4000),
            @xstate int;

    SELECT
      @error = ERROR_NUMBER(),
      @message = ERROR_MESSAGE(),
      @xstate = XACT_STATE();

    IF @xstate = -1
      ROLLBACK;
    IF @xstate = 1 AND @trancount = 0
      ROLLBACK
    IF @xstate = 1 AND @trancount > 0
      ROLLBACK TRANSACTION AddToCart;
END CATCH

----------------------------------------
CREATE PROCEDURE dbo.BookBaby
@customer_id INT,
@girl_id VARCHAR(MAX),
@vote INT = 0,
@status INT = 0
AS
BEGIN
	DECLARE @price INT = 0,
	@last_votes INT,
	@sales_date DATE;
	SELECT @price = price FROM girls gd WHERE gd.id = @girl_id
	SELECT @sales_date = GETDATE()
	IF(@status < 4)
	BEGIN
		INSERT INTO cart (customer_id, girl_id, price, vote_customer, sales_date, status) VALUES (@customer_id, @girl_id, @price, @vote, @sales_date, @status)
	END
END

----------------------------------------
CREATE PROCEDURE dbo.ChangeStatus
@kpi_id int,
@status_update int,
@vote int = null
AS
BEGIN
	declare @update_time date,
	@status_latest int,
	@girl_id int,
	@reviews_latest int
	select @update_time = GETDATE()
	select @status_latest = status from kpis where id = @kpi_id
	select @girl_id = girl_id, @vote = vote_customer from kpis where id = @kpi_id
	select @reviews_latest = reviews from girls where id = @girl_id
	if @status_latest < 3
	BEGIN
		if @status_update = 1 or @status_update = 2 or @status_update = 3
		BEGIN
			update kpis set status = @status_update where id = @kpi_id
		END
		if @status_update = 3
		BEGIN
			update kpis set status = @status_update where id = @kpi_id
			update girls set reviews = @reviews_latest + @vote where id = @girl_id
		END
	END
END

----------------------------------------
CREATE PROCEDURE dbo.ChangeStatus
@kpi_id int,
@status_update int,
@vote int = null
AS
BEGIN
	declare @update_time date,
	@status_latest int,
	@girl_id int,
	@reviews_latest int
	select @update_time = GETDATE()
	select @status_latest = status from kpis where id = @kpi_id
	select @girl_id = girl_id, @vote = vote_customer from kpis where id = @kpi_id
	select @reviews_latest = reviews from girls where id = @girl_id
	if @status_latest < 3
	BEGIN
		if @status_update = 1 or @status_update = 2 or @status_update = 3
		BEGIN
			update kpis set status = @status_update where id = @kpi_id
		END
		if @status_update = 3
		BEGIN
			update kpis set status = @status_update where id = @kpi_id
			update girls set reviews = @reviews_latest + @vote where id = @girl_id
		END
	END
END

----------------------------------------
CREATE PROCEDURE dbo.FillterHistoryCustomer
@PageNumber int = 1,
@PageSize int = 10,
@SortBy int = 1,
@SortDirection int = 0, -- DESC: 0, ASC: 1
@customer_id int = null,
@sales_date date = null,
@status int = null,
@price_start float = null,
@price_end float = null
AS
BEGIN
	DECLARE
	@offset int = (@PageNumber - 1) * @PageSize,
	@total int = 0
	IF @SortBy != 1 AND @SortBy != 2 AND @SortBy != 3 AND @SortBy != 4
	BEGIN
		SET @SortBy = 1
	END
	select c.id,
	c.first_name,
	c.last_name,
	k.girl_id,
	k.price,
	k.sales_date,
	k.status as status
	from customers c inner join cart k on c.id = k.customer_id
	where
		(@customer_id is null or c.id = @customer_id)
	and (@sales_date is null or k.sales_date = @sales_date)
	and (@status is null or k.status = @status)
	and (@price_start is null or k.price > @price_start)
	and (@price_end is null or k.price < @price_end)
	order by
		case when @SortBy = 1 and @SortDirection = 1 THEN sales_date END ASC,
	  	case when @SortBy = 1 and @SortDirection = 0 THEN sales_date END DESC,
	  	case when @SortBy = 2 and @SortDirection = 1 THEN price END ASC,
	  	case when @SortBy = 2 and @SortDirection = 0 THEN price END DESC,
	  	case when @SortBy = 3 and @SortDirection = 1 THEN status END ASC,
	  	case when @SortBy = 3 and @SortDirection = 0 THEN status END DESC
	offset (@offset) rows fetch next (@PageSize) rows only
END

----------------------------------------
CREATE PROCEDURE dbo.findGirlDetailByGirlId
@girlId varchar(max)
AS
BEGIN
	SELECT g.id as id, gd.girl_id as girlId, g.price, gd.marial_status as marialStatus, gd.nationality as nationality, gd.height, gd.weight, gd.round_ms_1 as roundMs1,
	gd.round_ms_2 as roundMs2, gd.round_ms_3 as roundMs3, gd.rating_vote as ratingVote FROM girl_detail gd  join girls g on gd.girl_id = g.id where gd.girl_id = @girlId
END

----------------------------------------
CREATE PROCEDURE dbo.GetAllBabies
AS
BEGIN
	select g.id as id, g.name as name, g.url as url, g.description as description, g.reviews as vote, g.price as price from girls g
END

----------------------------------------
CREATE PROCEDURE dbo.GetGirlDesByGirlId
@girlId int = 1
AS
BEGIN
	select * from girl_description gd where gd.girl_id = @girlId
END

----------------------------------------
CREATE PROCEDURE dbo.GetImagesBabyDetail
@girlId int = 1
AS
begin
	select * from girl_detail_image gd where gd.girl_id = @girlId
end

----------------------------------------
CREATE PROCEDURE dbo.GetPromoDetails
AS
SELECT * from promo_details
----------------------------------------
CREATE PROCEDURE dbo.GetUserDetail
@userId int
AS
DECLARE @rank nvarchar(20), @amoutSpent int, @total int, @totalDone int
SELECT @amoutSpent = SUM(CAST(c.price as int)) FROM cart c WHERE c.customer_id = @userId and c.status = 1
SELECT @totalDone = COUNT(CAST(c.price as int)) from cart c where c.customer_id = 1 and c.status = 1
SELECT @total = COUNT(CAST(c.price as int)) from cart c where c.customer_id = 1
IF @amoutSpent > 999 AND @amoutSpent < 2000
SET @rank = 'Sliver'
IF @amoutSpent > 1999 AND @amoutSpent < 5000
SET @rank = 'Gold'
IF @amoutSpent > 4999
SET @rank = 'Diamond'
IF @amoutSpent < 1000
SET @rank = 'Bronze'
SELECT @rank as Rank, ud.mail as Mail, ud.phone as PhoneNumber, @amoutSpent as AmountSpent, ud.avatar as Avatar, @totalDone as DoneTrans, @total as TotalTrans
FROM user_detail ud WHERE ud.user_id = @userId
----------------------------------------
CREATE PROCEDURE dbo.LikeBabies
@customer_id int,
@girl_id varchar
AS
BEGIN
	declare @like_date date,
	@last_votes int
	select @like_date = GETDATE()
	if @girl_id not in (select girl_id from like_baby lb where lb.customer_id = @customer_id )
	BEGIN
		insert into like_baby (girl_id, customer_id, like_date) values (@girl_id, @customer_id, @like_date)
		select @last_votes = reviews from girls gd where gd.id = @girl_id
		update girls set reviews = @last_votes + 1 where id = @girl_id
	END
END

----------------------------------------
CREATE PROCEDURE dbo.ListCart
@customer_id int

AS
BEGIN
	SELECT k.id as cartItemId, g.id as id, g.url as url, g.description as name, k.price as price, k.sales_date as salesDate, u.username as userName , u.full_name as fullName
	FROM cart k left join girls g on k.girl_id = g.id left join users u on k.customer_id = u.customer_id
	WHERE k.customer_id = @customer_id AND status = 0
END
----------------------------------------
CREATE PROCEDURE dbo.ListGirlTopReac
AS
select top 3 g.id as id, g.name as name, g.url as url, g.description as description, g.reviews as vote, g.price as price from girls g order by g.reviews desc
----------------------------------------
CREATE PROCEDURE dbo.LoginProc
@username varchar(max),
@password varchar(max)
AS
BEGIN
	select u.customer_id as customerId, u.full_name as fullName from users u where u.username = @username and u.password = @password
END
----------------------------------------
CREATE PROCEDURE dbo.RatingProc
@girl_id INT
AS
BEGIN
	DECLARE @rating_avg float
	select @rating_avg = ROUND(AVG(k.vote_customer),1) from kpis k where k.vote_customer != 0 AND k.status = 3 AND k.girl_id = @girl_id
	update girl_detail set rating_vote = @rating_avg where girl_id = @girl_id
	return @rating_avg
END

----------------------------------------
CREATE PROCEDURE dbo.RemoveCartItem
@cartItemId varchar(20)
AS
DELETE FROM cart WHERE id=@cartItemId
----------------------------------------
CREATE PROCEDURE dbo.RemoveGirl
@girl_id varchar(20)
AS
SET NOCOUNT ON
DECLARE @trancount int
SET @trancount = @@trancount;
BEGIN TRY
	   IF @trancount = 0
      BEGIN TRANSACTION
      ELSE
      SAVE TRANSACTION RemoveGirl
      DELETE FROM girls where id = @girl_id
      DELETE FROM girl_detail where girl_id = @girl_id
      DELETE FROM cart where girl_id = @girl_id
      lbexit:
      IF @trancount = 0
      COMMIT;
END TRY
BEGIN CATCH
    DECLARE @error int,
            @message varchar(4000),
            @xstate int;

    SELECT
      @error = ERROR_NUMBER(),
      @message = ERROR_MESSAGE(),
      @xstate = XACT_STATE();

    IF @xstate = -1
      ROLLBACK;
    IF @xstate = 1 AND @trancount = 0
      ROLLBACK
    IF @xstate = 1 AND @trancount > 0
      ROLLBACK TRANSACTION RemoveGirl;
END CATCH
----------------------------------------
