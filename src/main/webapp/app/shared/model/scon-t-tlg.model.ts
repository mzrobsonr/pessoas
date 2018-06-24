import { Moment } from 'moment';
import { ISconTLog } from 'app/shared/model//scon-t-log.model';

export interface ISconTTlg {
    id?: number;
    codigo?: string;
    descricao?: string;
    idtlg?: number;
    iddic?: number;
    usuario?: string;
    timestamp?: Moment;
    idtlgs?: ISconTLog[];
}

export class SconTTlg implements ISconTTlg {
    constructor(
        public id?: number,
        public codigo?: string,
        public descricao?: string,
        public idtlg?: number,
        public iddic?: number,
        public usuario?: string,
        public timestamp?: Moment,
        public idtlgs?: ISconTLog[]
    ) {}
}
