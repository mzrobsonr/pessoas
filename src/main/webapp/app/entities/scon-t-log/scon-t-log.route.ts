import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable } from 'rxjs';
import { SconTLog } from 'app/shared/model/scon-t-log.model';
import { SconTLogService } from './scon-t-log.service';
import { SconTLogComponent } from './scon-t-log.component';
import { SconTLogDetailComponent } from './scon-t-log-detail.component';
import { SconTLogUpdateComponent } from './scon-t-log-update.component';
import { SconTLogDeletePopupComponent } from './scon-t-log-delete-dialog.component';
import { ISconTLog } from 'app/shared/model/scon-t-log.model';

@Injectable({ providedIn: 'root' })
export class SconTLogResolve implements Resolve<ISconTLog> {
    constructor(private service: SconTLogService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).map((sconTLog: HttpResponse<SconTLog>) => sconTLog.body);
        }
        return Observable.of(new SconTLog());
    }
}

export const sconTLogRoute: Routes = [
    {
        path: 'scon-t-log',
        component: SconTLogComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pessoasApp.sconTLog.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'scon-t-log/:id/view',
        component: SconTLogDetailComponent,
        resolve: {
            sconTLog: SconTLogResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pessoasApp.sconTLog.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'scon-t-log/new',
        component: SconTLogUpdateComponent,
        resolve: {
            sconTLog: SconTLogResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pessoasApp.sconTLog.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'scon-t-log/:id/edit',
        component: SconTLogUpdateComponent,
        resolve: {
            sconTLog: SconTLogResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pessoasApp.sconTLog.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const sconTLogPopupRoute: Routes = [
    {
        path: 'scon-t-log/:id/delete',
        component: SconTLogDeletePopupComponent,
        resolve: {
            sconTLog: SconTLogResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pessoasApp.sconTLog.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
