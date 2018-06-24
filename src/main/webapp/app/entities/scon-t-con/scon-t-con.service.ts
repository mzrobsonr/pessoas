import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ISconTCon } from 'app/shared/model/scon-t-con.model';

type EntityResponseType = HttpResponse<ISconTCon>;
type EntityArrayResponseType = HttpResponse<ISconTCon[]>;

@Injectable({ providedIn: 'root' })
export class SconTConService {
    private resourceUrl = SERVER_API_URL + 'api/scon-t-cons';

    constructor(private http: HttpClient) {}

    create(sconTCon: ISconTCon): Observable<EntityResponseType> {
        return this.http.post<ISconTCon>(this.resourceUrl, sconTCon, { observe: 'response' });
    }

    update(sconTCon: ISconTCon): Observable<EntityResponseType> {
        return this.http.put<ISconTCon>(this.resourceUrl, sconTCon, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ISconTCon>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ISconTCon[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
