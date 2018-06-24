import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ISconTTlg } from 'app/shared/model/scon-t-tlg.model';

type EntityResponseType = HttpResponse<ISconTTlg>;
type EntityArrayResponseType = HttpResponse<ISconTTlg[]>;

@Injectable({ providedIn: 'root' })
export class SconTTlgService {
    private resourceUrl = SERVER_API_URL + 'api/scon-t-tlgs';

    constructor(private http: HttpClient) {}

    create(sconTTlg: ISconTTlg): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(sconTTlg);
        return this.http
            .post<ISconTTlg>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertDateFromServer(res));
    }

    update(sconTTlg: ISconTTlg): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(sconTTlg);
        return this.http
            .put<ISconTTlg>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertDateFromServer(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<ISconTTlg>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertDateFromServer(res));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ISconTTlg[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(sconTTlg: ISconTTlg): ISconTTlg {
        const copy: ISconTTlg = Object.assign({}, sconTTlg, {
            timestamp: sconTTlg.timestamp != null && sconTTlg.timestamp.isValid() ? sconTTlg.timestamp.format(DATE_FORMAT) : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.timestamp = res.body.timestamp != null ? moment(res.body.timestamp) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((sconTTlg: ISconTTlg) => {
            sconTTlg.timestamp = sconTTlg.timestamp != null ? moment(sconTTlg.timestamp) : null;
        });
        return res;
    }
}
