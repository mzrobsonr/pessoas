import { Moment } from 'moment';
import { ISconTUfe } from 'app/shared/model//scon-t-ufe.model';
import { ISconTBac } from 'app/shared/model//scon-t-bac.model';

export interface ISconTCid {
    id?: number;
    descricao?: string;
    idufe?: number;
    idcid?: number;
    codigo?: number;
    iddic?: number;
    iddic0?: number;
    cepcid?: string;
    codigoIbge?: string;
    codigoIbge0?: string;
    tipo?: number;
    usuario?: string;
    timestamp?: Moment;
    sconTUfe?: ISconTUfe;
    idufes?: ISconTBac[];
}

export class SconTCid implements ISconTCid {
    constructor(
        public id?: number,
        public descricao?: string,
        public idufe?: number,
        public idcid?: number,
        public codigo?: number,
        public iddic?: number,
        public iddic0?: number,
        public cepcid?: string,
        public codigoIbge?: string,
        public codigoIbge0?: string,
        public tipo?: number,
        public usuario?: string,
        public timestamp?: Moment,
        public sconTUfe?: ISconTUfe,
        public idufes?: ISconTBac[]
    ) {}
}
