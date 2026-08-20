insertion dans users:
     INSERT INTO users (
         id,
         ref,
         first_name,
         last_name,
         email,
         phone
     )
     VALUES
     (
         '1',
         'REF001',
         'Andi',
         'Razakaharison',
         'andi@gmail.com',
         '0340000001'
     ),
     (
         '2',
         'REF002',
         'Princy',
         'Andrianarivelo',
         'princy@gmail.com',
         '0340000002'
     ),
     (
         '3',
         'REF003',
         'Jean',
         'Rabe',
         'jean@gmail.com',
         '0340000003'
     );

     insertion dans cash_flows:

     INSERT INTO cash_flows (
         id,
         user_id,
         created_at,
         amount
     )
     VALUES
     (
         '1',
         'USR001',
         '2026-08-01T10:00:00+03:00',
         500000.00
     ),
     (
         '2',
         'USR001',
         '2026-08-02T14:30:00+03:00',
         150000.00
     ),
     (
         '3',
         'USR002',
         '2026-08-03T09:15:00+03:00',
         300000.00
     ),
     (
         '4',
         'USR002',
         '2026-08-05T16:00:00+03:00',
         75000.00
     ),

     INSERT INTO donations (
         id,
         comment
     )
     VALUES
     (
         '1',
         'Donation pour une association'
     ),
     (
         '3',
         'Aide financière à une famille'
     ),
     (
         '4',
         'Donation pour une œuvre caritative'
     );

Insertion dans expenses:
     INSERT INTO expenses (
         id,
         reason,
         frequency
     )
     VALUES
     (
         '2',
         'Achat de fournitures',
         'NONE'
     ),
     (
         '4',
         'Abonnement internet',
         'MONTHLY'
     );