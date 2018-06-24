/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { PessoasTestModule } from '../../../test.module';
import { SconTTlgUpdateComponent } from 'app/entities/scon-t-tlg/scon-t-tlg-update.component';
import { SconTTlgService } from 'app/entities/scon-t-tlg/scon-t-tlg.service';
import { SconTTlg } from 'app/shared/model/scon-t-tlg.model';

describe('Component Tests', () => {
    describe('SconTTlg Management Update Component', () => {
        let comp: SconTTlgUpdateComponent;
        let fixture: ComponentFixture<SconTTlgUpdateComponent>;
        let service: SconTTlgService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PessoasTestModule],
                declarations: [SconTTlgUpdateComponent]
            })
                .overrideTemplate(SconTTlgUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(SconTTlgUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SconTTlgService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new SconTTlg(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.sconTTlg = entity;
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
                    const entity = new SconTTlg();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.sconTTlg = entity;
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
