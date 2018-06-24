import { Moment } from 'moment';
import { ISconTBac } from 'app/shared/model//scon-t-bac.model';
import { ISconTLog } from 'app/shared/model//scon-t-log.model';

export interface ISconTLgb {
    id?: number;
    idlgb?: number;
    idlog?: number;
    idbac?: number;
    ceplgb?: string;
    larguralog?: number;
    usuario?: string;
    timestamp?: Moment;
    sconTBac?: ISconTBac;
    sconTLog?: ISconTLog;
}

export class SconTLgb implements ISconTLgb {
    constructor(
        public id?: number,
        public idlgb?: number,
        public idlog?: number,
        public idbac?: number,
        public ceplgb?: string,
        public larguralog?: number,
        public usuario?: string,
        public timestamp?: Moment,
        public sconTBac?: ISconTBac,
        public sconTLog?: ISconTLog
    ) {}
}
