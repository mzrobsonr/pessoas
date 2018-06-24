import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ISconTCid } from 'app/shared/model/scon-t-cid.model';

type EntityResponseType = HttpResponse<ISconTCid>;
type EntityArrayResponseType = HttpResponse<ISconTCid[]>;

@Injectable({ providedIn: 'root' })
export class SconTCidService {
    private resourceUrl = SERVER_API_URL + 'api/scon-t-cids';

    constructor(private http: HttpClient) {}

    create(sconTCid: ISconTCid): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(sconTCid);
        return this.http
            .post<ISconTCid>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertDateFromServer(res));
    }

    update(sconTCid: ISconTCid): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(sconTCid);
        return this.http
            .put<ISconTCid>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertDateFromServer(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<ISconTCid>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertDateFromServer(res));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ISconTCid[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(sconTCid: ISconTCid): ISconTCid {
        const copy: ISconTCid = Object.assign({}, sconTCid, {
            timestamp: sconTCid.timestamp != null && sconTCid.timestamp.isValid() ? sconTCid.timestamp.format(DATE_FORMAT) : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.timestamp = res.body.timestamp != null ? moment(res.body.timestamp) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((sconTCid: ISconTCid) => {
            sconTCid.timestamp = sconTCid.timestamp != null ? moment(sconTCid.timestamp) : null;
        });
        return res;
    }
}
