/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { PessoasTestModule } from '../../../test.module';
import { SconTBaiUpdateComponent } from 'app/entities/scon-t-bai/scon-t-bai-update.component';
import { SconTBaiService } from 'app/entities/scon-t-bai/scon-t-bai.service';
import { SconTBai } from 'app/shared/model/scon-t-bai.model';

describe('Component Tests', () => {
    describe('SconTBai Management Update Component', () => {
        let comp: SconTBaiUpdateComponent;
        let fixture: ComponentFixture<SconTBaiUpdateComponent>;
        let service: SconTBaiService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PessoasTestModule],
                declarations: [SconTBaiUpdateComponent]
            })
                .overrideTemplate(SconTBaiUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(SconTBaiUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SconTBaiService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new SconTBai(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.sconTBai = entity;
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
                    const entity = new SconTBai();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.sconTBai = entity;
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
