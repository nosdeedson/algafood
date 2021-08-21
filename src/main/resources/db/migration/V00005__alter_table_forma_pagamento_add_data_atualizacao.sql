alter table forma_pagamento add  data_atualizacao timestamp;
update forma_pagamento set data_atualizacao = (now() at time zone 'utc');
alter table forma_pagamento alter column data_atualizacao set not null;
