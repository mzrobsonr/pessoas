/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { PessoasTestModule } from '../../../test.module';
import { SconTConDetailComponent } from 'app/entities/scon-t-con/scon-t-con-detail.component';
import { SconTCon } from 'app/shared/model/scon-t-con.model';

describe('Component Tests', () => {
    describe('SconTCon Management Detail Component', () => {
        let comp: SconTConDetailComponent;
        let fixture: ComponentFixture<SconTConDetailComponent>;
        const route = ({ data: of({ sconTCon: new SconTCon(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PessoasTestModule],
                declarations: [SconTConDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(SconTConDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(SconTConDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.sconTCon).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
