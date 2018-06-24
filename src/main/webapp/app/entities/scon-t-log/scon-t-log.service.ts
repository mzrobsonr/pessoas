import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ISconTLog } from 'app/shared/model/scon-t-log.model';

type EntityResponseType = HttpResponse<ISconTLog>;
type EntityArrayResponseType = HttpResponse<ISconTLog[]>;

@Injectable({ providedIn: 'root' })
export class SconTLogService {
    private resourceUrl = SERVER_API_URL + 'api/scon-t-logs';

    constructor(private http: HttpClient) {}

    create(sconTLog: ISconTLog): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(sconTLog);
        return this.http
            .post<ISconTLog>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertDateFromServer(res));
    }

    update(sconTLog: ISconTLog): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(sconTLog);
        return this.http
            .put<ISconTLog>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertDateFromServer(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<ISconTLog>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertDateFromServer(res));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ISconTLog[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(sconTLog: ISconTLog): ISconTLog {
        const copy: ISconTLog = Object.assign({}, sconTLog, {
            timestamp: sconTLog.timestamp != null && sconTLog.timestamp.isValid() ? sconTLog.timestamp.format(DATE_FORMAT) : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.timestamp = res.body.timestamp != null ? moment(res.body.timestamp) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((sconTLog: ISconTLog) => {
            sconTLog.timestamp = sconTLog.timestamp != null ? moment(sconTLog.timestamp) : null;
        });
        return res;
    }
}
