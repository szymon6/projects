

create view Najmlodsza_flota
as

select  year (getdate())-AVG(year(s.data_powstania)) as srednia, l.nazwa from samolot s join linie_lotnicze l on s.id_Linie_lotnicze = l.id
group by l.nazwa
having AVG(year(s.data_powstania)) = (

select top 1 AVG(year(data_powstania)) as srednia from samolot
group by id_Linie_lotnicze 
order by srednia desc
)

go

select * from Najmlodsza_flota




create view Ilosc_pilotow
as

select count(id_Pilot) as ilosc, z.nazwa from pilot_zaloga p_z join zaloga z on p_z.id_Zaloga = z.id 
group by (z.nazwa)
having count(id_Pilot) = (

select top 1 count(id_Pilot) as ilosc from pilot_zaloga
group by id_Zaloga
order by ilosc desc

)

go


select * from Ilosc_pilotow


create proc wylot_przylot
@id_pasazer int 
as

declare @data_wylot date 
declare @czas_wylot time 
declare @miejsce_wylot varchar(30)

declare @data_przylot date 
declare @czas_przylot time 
declare @miejsce_przylot varchar(30)





set @data_wylot=(
select w.data from ZakupBiletu z join bilet b
on z.id_Bilet=b.id join lot l
on b.id_Lot = l.id join wylot w
on l.id_Wylot=w.id 
where z.id_Pasazer =@id_pasazer
)


set @czas_wylot =(
select w.czas from ZakupBiletu z join bilet b
on z.id_Bilet=b.id join lot l
on b.id_Lot = l.id join wylot w
on l.id_Wylot=w.id 
where z.id_Pasazer =@id_pasazer
)


set @miejsce_wylot=(
select Lotnisko.miasto from ZakupBiletu z join bilet b
on z.id_Bilet=b.id join lot l
on b.id_Lot = l.id join wylot w
on l.id_Wylot=w.id join lotnisko
on Lotnisko.id = w.id_Lotnisko
where z.id_Pasazer =@id_pasazer
)



set @data_przylot=(
select p.data from ZakupBiletu z join bilet b
on z.id_Bilet=b.id join lot l
on b.id_Lot = l.id join przylot p
on l.id_Przylot=p.id 
where z.id_Pasazer =@id_pasazer
)


set @czas_przylot=(
select p.czas from ZakupBiletu z join bilet b
on z.id_Bilet=b.id join lot l
on b.id_Lot = l.id join przylot p
on l.id_Przylot=p.id 
where z.id_Pasazer =@id_pasazer
)



set @miejsce_przylot=(
select Lotnisko.miasto from ZakupBiletu z join bilet b
on z.id_Bilet=b.id join lot l
on b.id_Lot = l.id join przylot p
on l.id_Przylot=p.id join lotnisko
on Lotnisko.id = p.id_Lotnisko
where z.id_Pasazer =@id_pasazer
)

select 
@data_wylot as 'data wylotu', 
@czas_wylot as 'czas wylotu', 
@miejsce_wylot as 'miejsce wylotu', 

@data_przylot as 'data przylotu', 
@czas_przylot as 'czas przylotu', 
@miejsce_przylot as 'miejsce przylotu' 

go


exec wylot_przylot 1




create proc ilosc_wylotow_przylotow
@id_lotnisko int,
@data date, 
@czas1 time, 
@czas2 time

as

declare @ilosc_przylotow int 

set @ilosc_przylotow=(
select count(id) from Przylot
where id_Lotnisko = 1 and data = @data and czas > @czas1 and czas <@czas2
)



declare @ilosc_wylotow int 

set @ilosc_wylotow=(
select count(id) from Wylot
where id_Lotnisko = 1 and data = @data and czas > @czas1 and czas <@czas2
)


select @ilosc_wylotow as 'ilosc wylotow', @ilosc_przylotow as 'ilosc przylotow'

go

exec ilosc_wylotow_przylotow 1 ,'2020-01-20','7:00','16:00'





create function roznica_penzji_recepcjonistow ()
returns money 
begin 

declare @roznica money
declare @najwyzsza money
declare @najnizsza money

set @najwyzsza=(select top 1 pensja from Recepcjonista order by pensja desc)

set @najnizsza=(select top 1 pensja from Recepcjonista order by pensja asc)

set @roznica=@najwyzsza-@najnizsza

return @roznica

end


select dbo.roznica_penzji_recepcjonistow ()





create function ilu_czlonkow_zalogi(@pilot_id int)
returns int 
begin

declare @id_zaloga int 
declare @ilosc_czlonkow int 

set @id_zaloga=(
select id_Zaloga from Pilot_zaloga
where id_Pilot =@pilot_id
)

set @ilosc_czlonkow=(
select count(id_Pilot) from Pilot_zaloga
where id_Zaloga = @id_zaloga
)
set @ilosc_czlonkow = @ilosc_czlonkow+(
select count(id_Stewardessa) from Stewardessa_zaloga
where id_Zaloga = @id_zaloga
)

return @ilosc_czlonkow
 
end


print dbo.ilu_czlonkow_zalogi(3)






CREATE TRIGGER pensja_stewardessa ON Stewardessa
AFTER insert 
AS


if EXISTS (select id from inserted
where pensja > 5000
)
BEGIN

 rollback

END

GO 




CREATE TRIGGER bagaz_ograniczenie ON Bagaz
AFTER insert 
AS


if EXISTS (select id from inserted
where waga > 20
)
BEGIN

 rollback

END

GO




CREATE TRIGGER usuwanie_lotu ON Wylot
after delete  
as

IF @@ROWCOUNT>1
ROLLBACK


if EXISTS (select id from Wylot 
where data = GETDATE()
)
BEGIN

 rollback

END

go



CREATE TRIGGER dodanie_pilota ON Pilot
AFTER insert 
AS

declare @ilosc_pilotow int  

set @ilosc_pilotow = (select COUNT(id) from Pilot)


if(@ilosc_pilotow >=10)
BEGIN

 rollback

END

GO 





 




















