import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GuantesPageComponent } from './guantes-page.component';

describe('GuantesPageComponent', () => {
  let component: GuantesPageComponent;
  let fixture: ComponentFixture<GuantesPageComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [GuantesPageComponent]
    });
    fixture = TestBed.createComponent(GuantesPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
