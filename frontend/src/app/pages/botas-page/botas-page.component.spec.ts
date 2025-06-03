import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BotasPageComponent } from './botas-page.component';

describe('BotasPageComponent', () => {
  let component: BotasPageComponent;
  let fixture: ComponentFixture<BotasPageComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [BotasPageComponent]
    });
    fixture = TestBed.createComponent(BotasPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
