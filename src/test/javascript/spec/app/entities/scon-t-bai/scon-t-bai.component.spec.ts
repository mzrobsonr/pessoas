/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { PessoasTestModule } from '../../../test.module';
import { SconTBaiComponent } from 'app/entities/scon-t-bai/scon-t-bai.component';
import { SconTBaiService } from 'app/entities/scon-t-bai/scon-t-bai.service';
import { SconTBai } from 'app/shared/model/scon-t-bai.model';

describe('Component Tests', () => {
    describe('SconTBai Management Component', () => {
        let comp: SconTBaiComponent;
        let fixture: ComponentFixture<SconTBaiComponent>;
        let service: SconTBaiService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PessoasTestModule],
                declarations: [SconTBaiComponent],
                providers: []
            })
                .overrideTemplate(SconTBaiComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(SconTBaiComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SconTBaiService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new SconTBai(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.sconTBais[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
