import { Moment } from 'moment';
import { ISconTBac } from 'app/shared/model//scon-t-bac.model';

export interface ISconTBai {
    id?: number;
    descricao?: string;
    idbai?: number;
    usuario?: string;
    timestamp?: Moment;
    idbais?: ISconTBac[];
}

export class SconTBai implements ISconTBai {
    constructor(
        public id?: number,
        public descricao?: string,
        public idbai?: number,
        public usuario?: string,
        public timestamp?: Moment,
        public idbais?: ISconTBac[]
    ) {}
}
