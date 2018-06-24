import { Moment } from 'moment';
import { ISconTCid } from 'app/shared/model//scon-t-cid.model';
import { ISconTBai } from 'app/shared/model//scon-t-bai.model';
import { ISconTLgb } from 'app/shared/model//scon-t-lgb.model';

export interface ISconTBac {
    id?: number;
    idbac?: number;
    idbai?: number;
    idcid?: number;
    cepcid?: string;
    usuario?: string;
    timestamp?: Moment;
    sconTCid?: ISconTCid;
    sconTBai?: ISconTBai;
    idbacs?: ISconTLgb[];
}

export class SconTBac implements ISconTBac {
    constructor(
        public id?: number,
        public idbac?: number,
        public idbai?: number,
        public idcid?: number,
        public cepcid?: string,
        public usuario?: string,
        public timestamp?: Moment,
        public sconTCid?: ISconTCid,
        public sconTBai?: ISconTBai,
        public idbacs?: ISconTLgb[]
    ) {}
}
