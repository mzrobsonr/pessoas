import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { PessoasSconTPaiModule } from './scon-t-pai/scon-t-pai.module';
import { PessoasSconTUfeModule } from './scon-t-ufe/scon-t-ufe.module';
import { PessoasSconTCidModule } from './scon-t-cid/scon-t-cid.module';
import { PessoasSconTBaiModule } from './scon-t-bai/scon-t-bai.module';
import { PessoasSconTBacModule } from './scon-t-bac/scon-t-bac.module';
import { PessoasSconTTlgModule } from './scon-t-tlg/scon-t-tlg.module';
import { PessoasSconTLogModule } from './scon-t-log/scon-t-log.module';
import { PessoasSconTLgbModule } from './scon-t-lgb/scon-t-lgb.module';
import { PessoasSconTConModule } from './scon-t-con/scon-t-con.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    // prettier-ignore
    imports: [
        PessoasSconTPaiModule,
        PessoasSconTUfeModule,
        PessoasSconTCidModule,
        PessoasSconTBaiModule,
        PessoasSconTBacModule,
        PessoasSconTTlgModule,
        PessoasSconTLogModule,
        PessoasSconTLgbModule,
        PessoasSconTConModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PessoasEntityModule {}
