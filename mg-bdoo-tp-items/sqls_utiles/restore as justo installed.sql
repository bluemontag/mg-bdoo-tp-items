use `item`;
Delete From historic_item;
delete from item;
Delete From transition;
Update workflow set initialState = null;
Delete From item_state;
Delete From team_user;
Delete From project_user;
Delete From project;
Delete From usuario Where user_name not like 'rodrigo';
Delete From item_type;
Delete From team;
Delete From workflow;



Update item_tracker 
    set 
        oid_admin_user = null 
    where 
        OID = 'e2890780-90f4-4909-97cd-3b6f2418ea47';

Delete From usuario;
Delete From item_tracker;

Insert into item_tracker 
    values (
        'e2890780-90f4-4909-97cd-3b6f2418ea47',
        0,
        0,
        null
    );

Insert into usuario values (
    'b4eaad6e-8141-4631-ad72-a20a8ebecc80', 
    0,
    'rodrigo',
    'rodrigo',
    0,
    'e2890780-90f4-4909-97cd-3b6f2418ea47'
    );

Update item_tracker 
    set 
        oid_admin_user = 'b4eaad6e-8141-4631-ad72-a20a8ebecc80' 
    where 
        OID = 'e2890780-90f4-4909-97cd-3b6f2418ea47';
    