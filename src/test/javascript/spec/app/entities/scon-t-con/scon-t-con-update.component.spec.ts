/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { PessoasTestModule } from '../../../test.module';
import { SconTConUpdateComponent } from 'app/entities/scon-t-con/scon-t-con-update.component';
import { SconTConService } from 'app/entities/scon-t-con/scon-t-con.service';
import { SconTCon } from 'app/shared/model/scon-t-con.model';

describe('Component Tests', () => {
    describe('SconTCon Management Update Component', () => {
        let comp: SconTConUpdateComponent;
        let fixture: ComponentFixture<SconTConUpdateComponent>;
        let service: SconTConService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PessoasTestModule],
                declarations: [SconTConUpdateComponent]
            })
                .overrideTemplate(SconTConUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(SconTConUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SconTConService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new SconTCon(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.sconTCon = entity;
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
                    const entity = new SconTCon();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.sconTCon = entity;
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
