import { Moment } from 'moment';
import { ISconTUfe } from 'app/shared/model//scon-t-ufe.model';

export interface ISconTPai {
    id?: number;
    descricao?: string;
    idpai?: number;
    usuario?: string;
    timestamp?: Moment;
    idpais?: ISconTUfe[];
}

export class SconTPai implements ISconTPai {
    constructor(
        public id?: number,
        public descricao?: string,
        public idpai?: number,
        public usuario?: string,
        public timestamp?: Moment,
        public idpais?: ISconTUfe[]
    ) {}
}
