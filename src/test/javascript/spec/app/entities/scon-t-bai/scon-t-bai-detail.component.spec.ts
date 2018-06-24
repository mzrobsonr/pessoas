/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { PessoasTestModule } from '../../../test.module';
import { SconTBaiDetailComponent } from 'app/entities/scon-t-bai/scon-t-bai-detail.component';
import { SconTBai } from 'app/shared/model/scon-t-bai.model';

describe('Component Tests', () => {
    describe('SconTBai Management Detail Component', () => {
        let comp: SconTBaiDetailComponent;
        let fixture: ComponentFixture<SconTBaiDetailComponent>;
        const route = ({ data: of({ sconTBai: new SconTBai(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PessoasTestModule],
                declarations: [SconTBaiDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(SconTBaiDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(SconTBaiDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.sconTBai).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
