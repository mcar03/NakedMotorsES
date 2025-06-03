import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RuedasPageComponent } from './ruedas-page.component';

describe('RuedasPageComponent', () => {
  let component: RuedasPageComponent;
  let fixture: ComponentFixture<RuedasPageComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [RuedasPageComponent]
    });
    fixture = TestBed.createComponent(RuedasPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
