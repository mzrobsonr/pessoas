/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { PessoasTestModule } from '../../../test.module';
import { SconTLgbDetailComponent } from 'app/entities/scon-t-lgb/scon-t-lgb-detail.component';
import { SconTLgb } from 'app/shared/model/scon-t-lgb.model';

describe('Component Tests', () => {
    describe('SconTLgb Management Detail Component', () => {
        let comp: SconTLgbDetailComponent;
        let fixture: ComponentFixture<SconTLgbDetailComponent>;
        const route = ({ data: of({ sconTLgb: new SconTLgb(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PessoasTestModule],
                declarations: [SconTLgbDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(SconTLgbDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(SconTLgbDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.sconTLgb).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
