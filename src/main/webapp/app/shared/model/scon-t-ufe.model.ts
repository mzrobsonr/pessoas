import { Moment } from 'moment';
import { ISconTPai } from 'app/shared/model//scon-t-pai.model';
import { ISconTCid } from 'app/shared/model//scon-t-cid.model';

export interface ISconTUfe {
    id?: number;
    descricao?: string;
    idufe?: number;
    idpai?: number;
    codigo?: number;
    codigoIbge?: string;
    usuario?: string;
    timestamp?: Moment;
    sconTPai?: ISconTPai;
    idufes?: ISconTCid[];
}

export class SconTUfe implements ISconTUfe {
    constructor(
        public id?: number,
        public descricao?: string,
        public idufe?: number,
        public idpai?: number,
        public codigo?: number,
        public codigoIbge?: string,
        public usuario?: string,
        public timestamp?: Moment,
        public sconTPai?: ISconTPai,
        public idufes?: ISconTCid[]
    ) {}
}
