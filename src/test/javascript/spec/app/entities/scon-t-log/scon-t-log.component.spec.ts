/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { PessoasTestModule } from '../../../test.module';
import { SconTLogComponent } from 'app/entities/scon-t-log/scon-t-log.component';
import { SconTLogService } from 'app/entities/scon-t-log/scon-t-log.service';
import { SconTLog } from 'app/shared/model/scon-t-log.model';

describe('Component Tests', () => {
    describe('SconTLog Management Component', () => {
        let comp: SconTLogComponent;
        let fixture: ComponentFixture<SconTLogComponent>;
        let service: SconTLogService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PessoasTestModule],
                declarations: [SconTLogComponent],
                providers: []
            })
                .overrideTemplate(SconTLogComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(SconTLogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SconTLogService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new SconTLog(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.sconTLogs[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
