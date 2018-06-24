/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { PessoasTestModule } from '../../../test.module';
import { SconTCidUpdateComponent } from 'app/entities/scon-t-cid/scon-t-cid-update.component';
import { SconTCidService } from 'app/entities/scon-t-cid/scon-t-cid.service';
import { SconTCid } from 'app/shared/model/scon-t-cid.model';

describe('Component Tests', () => {
    describe('SconTCid Management Update Component', () => {
        let comp: SconTCidUpdateComponent;
        let fixture: ComponentFixture<SconTCidUpdateComponent>;
        let service: SconTCidService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PessoasTestModule],
                declarations: [SconTCidUpdateComponent]
            })
                .overrideTemplate(SconTCidUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(SconTCidUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SconTCidService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new SconTCid(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.sconTCid = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.update).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );

            it(
                'Should call create service on save for new entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new SconTCid();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.sconTCid = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.create).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );
        });
    });
});
