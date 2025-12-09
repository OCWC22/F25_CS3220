-- 1. List the users whose birthday is in the year of 2004.
select
    first_name,
    last_name,
    birthday
from lab10_users
where year(birthday) = 2004;

-- 2. List all the users by their age in descending order.
select
    first_name,
    last_name,
    timestampdiff(year, birthday, curdate()) as age
from lab10_users
order by age desc;

-- 3. List all the unique area code from the phone numbers in ascending order.
select distinct
    area_code
from lab10_phone_numbers
order by area_code asc;

-- 4. List all the unique area code and the total number of phone numbers that are related to the area code in ascending order by the area code.
select
    area_code,
    count(*) as phone_count
from lab10_phone_numbers
group by area_code
order by area_code asc;

-- 5. List all the phone numbers that is type of "mobile".
select distinct
    pn.area_code,
    pn.phone_number
from lab10_phone_numbers pn
join lab10_user_phone_contacts upc on pn.id = upc.phone_number_id
join lab10_contact_types ct on upc.contact_type_id = ct.id
where ct.type = 'Mobile';

-- 6. List all the phone numbers and contact type related to John.
select distinct
    pn.area_code,
    pn.phone_number,
    ct.type as contact_type
from lab10_users u
join lab10_user_phone_contacts upc on u.id = upc.user_id
join lab10_phone_numbers pn on upc.phone_number_id = pn.id
join lab10_contact_types ct on upc.contact_type_id = ct.id
where u.first_name = 'John';

-- 7. Find the total number of phone numbers that are related to Jane.
select
    count(distinct pn.id) as jane_phone_number_total
from lab10_users u
join lab10_user_phone_contacts upc on u.id = upc.user_id
join lab10_phone_numbers pn on upc.phone_number_id = pn.id
where u.first_name = 'Jane';

-- 8. Find all phone numbers and their contact type that are related to John and Jane at the same time.
select distinct
    pn.area_code,
    pn.phone_number,
    ct.type as contact_type
from lab10_user_phone_contacts upc
join lab10_phone_numbers pn on upc.phone_number_id = pn.id
join lab10_contact_types ct on upc.contact_type_id = ct.id
where exists (
    select 1
    from lab10_user_phone_contacts upc_john
    join lab10_users u_john on u_john.id = upc_john.user_id
    where upc_john.phone_number_id = pn.id
      and u_john.first_name = 'John'
)
and exists (
    select 1
    from lab10_user_phone_contacts upc_jane
    join lab10_users u_jane on u_jane.id = upc_jane.user_id
    where upc_jane.phone_number_id = pn.id
      and u_jane.first_name = 'Jane'
);
