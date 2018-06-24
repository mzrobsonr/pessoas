import { Moment } from 'moment';
import { ISconTTlg } from 'app/shared/model//scon-t-tlg.model';
import { ISconTLgb } from 'app/shared/model//scon-t-lgb.model';

export interface ISconTLog {
    id?: number;
    ceplog?: string;
    descricao?: string;
    idlog?: number;
    idtlg?: number;
    usuario?: string;
    timestamp?: Moment;
    sconTTlg?: ISconTTlg;
    idlogs?: ISconTLgb[];
}

export class SconTLog implements ISconTLog {
    constructor(
        public id?: number,
        public ceplog?: string,
        public descricao?: string,
        public idlog?: number,
        public idtlg?: number,
        public usuario?: string,
        public timestamp?: Moment,
        public sconTTlg?: ISconTTlg,
        public idlogs?: ISconTLgb[]
    ) {}
}
