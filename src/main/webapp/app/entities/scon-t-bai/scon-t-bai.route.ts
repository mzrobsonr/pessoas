import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable } from 'rxjs';
import { SconTBai } from 'app/shared/model/scon-t-bai.model';
import { SconTBaiService } from './scon-t-bai.service';
import { SconTBaiComponent } from './scon-t-bai.component';
import { SconTBaiDetailComponent } from './scon-t-bai-detail.component';
import { SconTBaiUpdateComponent } from './scon-t-bai-update.component';
import { SconTBaiDeletePopupComponent } from './scon-t-bai-delete-dialog.component';
import { ISconTBai } from 'app/shared/model/scon-t-bai.model';

@Injectable({ providedIn: 'root' })
export class SconTBaiResolve implements Resolve<ISconTBai> {
    constructor(private service: SconTBaiService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).map((sconTBai: HttpResponse<SconTBai>) => sconTBai.body);
        }
        return Observable.of(new SconTBai());
    }
}

export const sconTBaiRoute: Routes = [
    {
        path: 'scon-t-bai',
        component: SconTBaiComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pessoasApp.sconTBai.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'scon-t-bai/:id/view',
        component: SconTBaiDetailComponent,
        resolve: {
            sconTBai: SconTBaiResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pessoasApp.sconTBai.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'scon-t-bai/new',
        component: SconTBaiUpdateComponent,
        resolve: {
            sconTBai: SconTBaiResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pessoasApp.sconTBai.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'scon-t-bai/:id/edit',
        component: SconTBaiUpdateComponent,
        resolve: {
            sconTBai: SconTBaiResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pessoasApp.sconTBai.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const sconTBaiPopupRoute: Routes = [
    {
        path: 'scon-t-bai/:id/delete',
        component: SconTBaiDeletePopupComponent,
        resolve: {
            sconTBai: SconTBaiResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pessoasApp.sconTBai.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
